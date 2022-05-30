package com.taufufah.ehailing;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface DriverRepository extends Neo4jRepository<Driver, Long> {
    // @Query("MATCH (driver:Driver) WHERE driver.status = $status RETURN driver")
    List<Driver> findAllByStatus(Status status);

    @Query("MATCH (d:Driver) MATCh (v:Vertex) WHERE id(d) = $driverId AND id(v) = $vertexId CREATE (d)-[:CONNECTED_VERTEX {distance: point.distance(point({x:d.longitude, y:d.latitude}), point({x:v.longitude, y:v.latitude}))}]->(v)")
    void connectToClosestVertex(Long driverId, Long vertexId);

    @Query("MATCH (d:Driver) WHERE id(d) = $driverId set d.longitude = $longitude, d.latitude = $latitude")
    void updateDriverLocation(Long driverId, Double longitude, Double latitude);
}
