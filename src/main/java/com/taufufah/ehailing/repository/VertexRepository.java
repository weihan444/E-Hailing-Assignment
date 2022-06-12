package com.taufufah.ehailing.repository;

import com.taufufah.ehailing.model.Vertex;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface VertexRepository extends Neo4jRepository<Vertex, Long> {
    @Query("MATCH (v:Vertex)" +
            "WITH v, point.distance(point({x:$x, y:$y}), point({x:v.longitude, y:v.latitude})) as distance " +
            "ORDER BY distance LIMIT 1 RETURN v")
    Vertex findClosestNodes(Double x, Double y);

    @Query("MATCH (v1:Vertex) MATCH (v2:Vertex) WHERE v1.longitude = $x1 AND v1.latitude = $y1 AND v2.longitude = $x2 AND v2.latitude = $y2 CREATE (v1)-[:CONNECTED_VERTEX {distance: point.distance(point({x:v1.longitude, y:v1.latitude}), point({x:v2.longitude, y:v2.latitude}))}]->(v2) RETURN v2")
    Vertex connectVertex(double x1, double y1, double x2, double y2);
}
