package com.taufufah.ehailing.repository;

import java.util.List;

import com.taufufah.ehailing.model.Driver;
import com.taufufah.ehailing.model.Status;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface DriverRepository extends Neo4jRepository<Driver, Long> {
    // @Query("MATCH (driver:Driver) WHERE driver.status = $status RETURN driver")
    List<Driver> findAllByStatus(Status status);

    // @Query("MATCH (d:Driver) WHERE d.name =~ '.*$name.*' RETURN d")
    List<Driver> findOneByName(String name);

    @Query("MATCH (d:Driver) MATCH (v:Vertex) WHERE id(d) = $driverId AND id(v) = $vertexId CREATE (d)-[:CONNECTED_VERTEX {distance: point.distance(point({x:d.longitude, y:d.latitude}), point({x:v.longitude, y:v.latitude}))}]->(v) RETURN d")
    Driver connectToClosestVertex(Long driverId, Long vertexId);

    @Query("MATCH (d:Driver) WHERE id(d) = $driverId set d.longitude = $longitude, d.latitude = $latitude RETURN d")
    Driver updateDriverLocation(Long driverId, Double longitude, Double latitude);

    @Query("MATCH (d:Driver) MATCH(c:Customer) WHERE id(d) = $driverId AND id(c) = $customerId CREATE (d)-[:FETCHING]->(c) RETURN d")
    Driver fetchCustomer(Long driverId, Long customerId);

    @Query("MATCH (d:Driver) WHERE id(d) = $driverId SET d.status = $status RETURN d")
    Driver updateDriverStatus(Long driverId, Status status);

    @Query("MATCH (d:Driver)-[r:FETCHING]->() WHERE id(d) = $driverId DELETE r RETURN d")
    Driver deleteFetching(Long driverId);

    @Query("MATCH (d:Driver)-[r:CONNECTED_VERTEX]->() WHERE id(d) = $driverId DELETE r RETURN d")
    Driver deleteConnectedVertex(Long driverId);

    @Query("MATCH (d:Driver)-[r:FETCHING]->(m) WHERE id(m) = $customerId return d LIMIT 1")
    Driver findDriverByCustomer(Long customerId);

    @Query("MATCH (d:Driver) WHERE id(d) = $driverId SET d.rating = d.rating + $rating SET d.ratingCount = d.ratingCount + 1 RETURN d")
    Driver addRating(Long driverId, Integer rating);
}
