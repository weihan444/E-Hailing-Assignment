package com.taufufah.ehailing.repository;

import java.util.List;

import com.taufufah.ehailing.model.Customer;
import com.taufufah.ehailing.model.Status;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
    List<Customer> findAllByStatus(Status status);

    @Query("MATCH (c:Customer) MATCH (v:Vertex) WHERE id(c) = $customerId AND id(v) = $vertexId CREATE (c)-[:CONNECTED_VERTEX {distance: point.distance(point({x:c.longitude, y:c.latitude}), point({x:v.longitude, y:v.latitude}))}]->(v) RETURN c")
    Customer connectToClosestVertex(Long customerId, Long vertexId);

    @Query("MATCH (c:Customer) WHERE id(c) = $customerId SET c.status = $status RETURN c")
    Customer updateCustomerStatus(Long customerId, Status status);

    @Query("MATCH (c:Customer) MATCH (d:Destination) WHERE id(c) = $customerId AND id(d) = $destId CREATE (c)-[:GOING_TO]->(d) RETURN c")
    Customer updateDestination(Long customerId, Long destId);
}
