package com.taufufah.ehailing;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
    @Query("MATCH (c:Customer) MATCh (v:Vertex) WHERE id(c) = $customerId AND id(v) = $vertexId CREATE (c)-[:CONNECTED_VERTEX {distance: point.distance(point({x:c.longitude, y:c.latitude}), point({x:v.longitude, y:v.latitude}))}]->(v)")
    void connectToClosestVertex(Long customerId, Long vertexId);

    @Query("MATCH (c:Customer) WHERE id(c) = $customerId SET c.status = $status")
    void updateCustomerStatus(Long customerId, Status status);

    @Query("MATCH (c:Customer) MATCH (d:Destination) WHERE id(c) = $customerId AND id(d) = $destId CREATE (c)-[:GOING_TO]->(d)")
    void updateDestination(Long customerId, Long destId);
}
