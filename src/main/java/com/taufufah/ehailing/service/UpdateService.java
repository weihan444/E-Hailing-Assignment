package com.taufufah.ehailing.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.taufufah.ehailing.exceptions.CustomerNotFoundException;
import com.taufufah.ehailing.model.Customer;
import com.taufufah.ehailing.model.Distance;
import com.taufufah.ehailing.model.Driver;
import com.taufufah.ehailing.model.Status;
import com.taufufah.ehailing.repository.CustomerRepository;
import com.taufufah.ehailing.repository.DestinationRepository;
import com.taufufah.ehailing.repository.DriverRepository;
import com.taufufah.ehailing.repository.VertexRepository;

import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    private final Neo4jClient neo4jClient;
    private final DriverRepository driverRepository;
    private final VertexRepository vertexRepository;
    private final CustomerRepository customerRepository;
    private final DestinationRepository destinationRepository;
    private List<Path> shortestPathList = new ArrayList<>();
    // private final Logger logger = LoggerFactory.getLogger(UpdateService.class);

    public UpdateService(Neo4jClient neo4jClient, DriverRepository driverRepository, VertexRepository vertexRepository,
            CustomerRepository customerRepository, DestinationRepository destinationRepository) {
        this.neo4jClient = neo4jClient;
        this.driverRepository = driverRepository;
        this.vertexRepository = vertexRepository;
        this.customerRepository = customerRepository;
        this.destinationRepository = destinationRepository;
    }

    public void findShortestPath(Long node1Id, Long node2Id) {
        Collection<Map<String, Object>> results = neo4jClient.query(
                "MATCH (n) MATCH (m) MATCH path = shortestPath((n)-[:CONNECTED_VERTEX*..50]-(m)) WHERE Id(n) = $node1Id AND Id(m) = $node2Id return nodes(path) as p, relationships(path) as d")
                .bindAll(Map.of("node1Id", node1Id, "node2Id", node2Id)).fetch().all();

        for (Map<String, Object> row : results) {
            List<Node> path = (List<Node>) row.get("p");
            List<Relationship> distance = (List<Relationship>) row.get("d");
            shortestPathList.add(new Path(new ArrayList<>(path), new ArrayList<>(distance)));
        }
    }

    public Integer findShortestTime(Long driverId, Long customerId) {
        Collection<Map<String, Object>> results = neo4jClient.query(
                "MATCH (n) MATCH (m) MATCH path = shortestPath((n)-[:CONNECTED_VERTEX*..50]-(m)) WHERE Id(n) = $node1Id AND Id(m) = $node2Id return relationships(path) as d")
                .bindAll(Map.of("node1Id", driverId, "node2Id", customerId)).fetch().all();

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Collection<Map<String, Object>> resultsDest = neo4jClient.query(
                "MATCH (n) MATCH (m) MATCH path = shortestPath((n)-[:CONNECTED_VERTEX*..50]-(m)) WHERE Id(n) = $node1Id AND Id(m) = $node2Id return relationships(path) as d")
                .bindAll(Map.of("node1Id", customerId, "node2Id", customer.getDestination().getId())).fetch().all();

        Integer time = 0;

        for (Map<String, Object> result : results) {
            List<Relationship> paths = (List<Relationship>) result.get("d");
            for (Relationship path : paths) {
                Double distance = path.get("distance").asDouble();
                while (distance > 0) {
                    distance -= 5;
                    time++;
                }
                time++;
            }
        }
        for (Map<String, Object> result : resultsDest) {
            List<Relationship> paths = (List<Relationship>) result.get("d");
            for (Relationship path : paths) {
                Double distance = path.get("distance").asDouble();
                while (distance > 0) {
                    distance -= 5;
                    time++;
                }
                time++;
            }
        }
        return time;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void updateDriver() {
        for (Path shortestPath : shortestPathList) {
            Long driverId = null;

            if (shortestPath.getDriver().labels().toString().equals("[Customer]")) {
                Long customerId = shortestPath.getDriver().id();
                driverId = driverRepository.findDriverByCustomer(customerId).getId();
            } else {
                driverId = shortestPath.getDriver().id();
            }

            Driver driver = driverRepository.findById(driverId).get();

            if (shortestPath.getPathList().size() > 0) {
                shortestPath.decreaseDistance(0, 5.0, driver);
            } else if (shortestPath.getPathList().size() == 0) {
                shortestPathList.remove(shortestPath);
                if (driver.getCustomer().getStatus().equals(Status.WAITING)) {
                    driverRepository.updateDriverLocation(driverId,
                            driver.getCustomer().getLongitude(),
                            driver.getCustomer().getLatitude());
                    customerRepository.updateCustomerStatus(driver.getCustomer().getId(), Status.PICKED_UP);
                    findShortestPath(driver.getCustomer().getId(), driver.getCustomer().getDestination().getId());
                } else {
                    driverRepository.updateDriverLocation(driverId,
                            driver.getCustomer().getDestination().getLongitude(),
                            driver.getCustomer().getDestination().getLatitude());
                    customerRepository.updateCustomerStatus(driver.getCustomer().getId(), Status.REACHED);
                    destinationRepository.delete(driver.getCustomer().getDestination());
                    driverRepository.updateDriverStatus(driverId, Status.AVAILABLE);
                    driverRepository.deleteFetching(driverId);
                    driverRepository.deleteConnectedVertex(driverId);
                    driverRepository.connectToClosestVertex(driverId,
                            vertexRepository.findClosestNodes(driver.getLongitude(), driver.getLatitude()).getId());
                }

            }

            // logger.info(driver.getName());
            // logger.info("Distance Left: " + shortestPath.getDistanceLeft(0));
            // logger.info(shortestPath.getxVel() + " " + shortestPath.getyVel());
            // logger.info("x:" + driver.getLongitude() + " y:" + driver.getLatitude());
        }
    }

    public List<Path> getShortestPathList() {
        return shortestPathList;
    }

    class Path {
        private Node from;
        private List<Node> pathList;
        private List<Relationship> distanceList;
        private List<Double> distanceLeft;
        private Double xVel;
        private Double yVel;

        public Path(List<Node> path, List<Relationship> distance) {
            this.from = path.remove(0);
            this.pathList = path;
            this.distanceList = distance;
            this.distanceLeft = new ArrayList<>();
            this.xVel = (path.get(0).get("longitude").asDouble() - from.get("longitude").asDouble()) / getDistance(0)
                    * 5;
            this.yVel = (path.get(0).get("latitude").asDouble() - from.get("latitude").asDouble()) / getDistance(0) * 5;
            for (int i = 0; i < distanceList.size(); i++) {
                distanceLeft.add(getDistance(i));
            }
        }

        public Node getDriver() {
            return from;
        }

        public List<Node> getPathList() {
            return pathList;
        }

        public void setPathList(List<Node> path) {
            this.pathList = path;
        }

        public List<Relationship> getDistanceList() {
            return distanceList;
        }

        public void setDistanceList(List<Relationship> distance) {
            this.distanceList = distance;
        }

        public void setDistanceLeft(int index, Double distance) {
            distanceLeft.set(index, distance);
        }

        public void decreaseDistance(int index, Double distance, Driver driver) {
            setDistanceLeft(index, getDistanceLeft(index) - distance);
            if (getDistanceLeft(0) < 0) {
                driverRepository.updateDriverLocation(driver.getId(), pathList.get(0).get("longitude").asDouble(),
                        pathList.get(0).get("latitude").asDouble());
                if (pathList.size() > 1) {
                    this.xVel = (pathList.get(1).get("longitude").asDouble()
                            - pathList.get(0).get("longitude").asDouble())
                            / getDistance(1) * 5;
                    this.yVel = (pathList.get(1).get("latitude").asDouble()
                            - pathList.get(0).get("latitude").asDouble())
                            / getDistance(1) * 5;
                }
                pathList.remove(0);
                distanceList.remove(0);
                distanceLeft.remove(0);
            } else {
                driverRepository.updateDriverLocation(driver.getId(), driver.getLongitude() + getxVel(),
                        driver.getLatitude() + getyVel());
            }
        }

        public Double getDistance(int index) {
            return distanceList.get(index).get("distance").asDouble();
        }

        public Double getDistanceLeft(int index) {
            return distanceLeft.get(index);
        }

        public Double getxVel() {
            return xVel;
        }

        public void setxVel(Double xVel) {
            this.xVel = xVel;
        }

        public Double getyVel() {
            return yVel;
        }

        public void setyVel(Double yVel) {
            this.yVel = yVel;
        }
    }
}
