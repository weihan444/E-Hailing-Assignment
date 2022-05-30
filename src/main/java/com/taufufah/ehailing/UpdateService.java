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
    private List<Pair> shortestPath = new ArrayList<>();
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
            shortestPath.add(new Pair(new ArrayList<>(path), new ArrayList<>(distance)));
        }

    }

    public List<Pair> getShortestPath() {
        return shortestPath;
    }

    @Scheduled(fixedDelay = 1000)
    public void updateDriver() {
        logger.info(shortestPath.get(0).getPath() + "");
        if (shortestPath.get(0).getPath().size() != shortestPath.get(0).getDistance().size()) {
            shortestPath.get(0).getPath().remove(0);
        }
    }

}

class Pair {
    private List<Node> path;
    private List<Relationship> distance;

    public Pair(List<Node> path, List<Relationship> distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }

    public List<Relationship> getDistance() {
        return distance;
    }

    public void setDistance(List<Relationship> distance) {
        this.distance = distance;
    }

}
