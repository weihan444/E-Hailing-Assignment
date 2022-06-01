package com.taufufah.ehailing.repository;

import com.taufufah.ehailing.model.Destination;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface DestinationRepository extends Neo4jRepository<Destination, Long> {
    @Query("MATCH (d:Destination) MATCh (v:Vertex) WHERE id(d) = $destId AND id(v) = $vertexId CREATE (d)-[:CONNECTED_VERTEX {distance: point.distance(point({x:d.longitude, y:d.latitude}), point({x:v.longitude, y:v.latitude}))}]->(v)")
    void connectToClosestVertex(Long destId, Long vertexId);
}
