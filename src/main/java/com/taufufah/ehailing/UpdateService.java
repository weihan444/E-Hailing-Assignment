package com.taufufah.ehailing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    private List<Path> shortestPathList = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(UpdateService.class);

    public UpdateService(Neo4jClient neo4jClient, DriverRepository driverRepository, VertexRepository vertexRepository,
            CustomerRepository customerRepository) {
        this.neo4jClient = neo4jClient;
        this.driverRepository = driverRepository;
        this.vertexRepository = vertexRepository;
        this.customerRepository = customerRepository;
    }

    public void findShortestPath(Long driverId, Long customerId) {
        Collection<Map<String, Object>> results = neo4jClient.query(
                "MATCH (d:Driver) MATCH (c:Customer) MATCH path = shortestPath((d)-[:CONNECTED_VERTEX*..50]-(c)) WHERE Id(d) = $driverId AND Id(c) = $customerId return nodes(path) as p, relationships(path) as d")
                .bindAll(Map.of("driverId", driverId, "customerId", customerId)).fetch().all();

        for (Map<String, Object> row : results) {
            List<Node> path = (List<Node>) row.get("p");
            List<Relationship> distance = (List<Relationship>) row.get("d");
            shortestPathList.add(new Path(new ArrayList<>(path), new ArrayList<>(distance)));
        }
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void updateDriver() {
        for (Path shortestPath : shortestPathList) {
            if (shortestPath.getPathList().size() > 0) {
                shortestPath.decreaseDistance(0, 1.0);
            }

            Long driverId = shortestPath.getDriver().id();

            Driver driver = driverRepository.findById(driverId).get();

            if (shortestPath.getPathList().size() == 0) {
                driverRepository.updateDriverLocation(driverId, driver.getCustomer().getCurr_longitude(),
                        driver.getCustomer().getCurr_latitude());
            } else {
                driverRepository.updateDriverLocation(driverId, driver.getLongitude() + shortestPath.getxVel(),
                        driver.getLatitude() + shortestPath.getyVel());
            }

            logger.info(shortestPath.getxVel() + "");
            logger.info(shortestPath.getyVel() + "");
            // logger.info(shortestPath.get(0).getPathList() + "");
            logger.info("x:" + driver.getLongitude() + " y:" + driver.getLatitude());
        }
    }

    public List<Path> getShortestPathList() {
        return shortestPathList;
    }
}

class Path {
    private Node driver;
    private List<Node> pathList;
    private List<Relationship> distanceList;
    private List<Double> distanceLeft;
    private Double xVel;
    private Double yVel;

    public Path(List<Node> path, List<Relationship> distance) {
        this.driver = path.remove(0);
        this.pathList = path;
        this.distanceList = distance;
        this.distanceLeft = new ArrayList<>();
        this.xVel = (path.get(0).get("longitude").asDouble() - driver.get("longitude").asDouble()) / getDistance(0);
        this.yVel = (path.get(0).get("latitude").asDouble() - driver.get("latitude").asDouble()) / getDistance(0);
        for (int i = 0; i < distanceList.size(); i++) {
            distanceLeft.add(getDistance(i));
        }
    }

    public Node getDriver() {
        return driver;
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

    public void decreaseDistance(int index, Double distance) {
        setDistanceLeft(index, getDistanceLeft(index) - distance);
        if (getDistanceLeft(0) < 0) {
            if (pathList.size() == 2) {
                this.xVel = (pathList.get(1).get("curr_longitude").asDouble()
                        - pathList.get(0).get("longitude").asDouble())
                        / getDistance(1);
                this.yVel = (pathList.get(1).get("curr_latitude").asDouble()
                        - pathList.get(0).get("latitude").asDouble())
                        / getDistance(1);
            } else if (pathList.size() > 2) {
                this.xVel = (pathList.get(1).get("longitude").asDouble() - pathList.get(0).get("longitude").asDouble())
                        / getDistance(1);
                this.yVel = (pathList.get(1).get("latitude").asDouble() - pathList.get(0).get("latitude").asDouble())
                        / getDistance(1);
            } else {

            }

            pathList.remove(0);
            distanceList.remove(0);
            distanceLeft.remove(0);
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
