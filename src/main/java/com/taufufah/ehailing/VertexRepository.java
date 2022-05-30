package com.taufufah.ehailing;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface VertexRepository extends Neo4jRepository<Vertex, Long> {
    @Query("MATCH (v:Vertex)" +
            "WITH v, point.distance(point({x:$x, y:$y}), point({x:v.longitude, y:v.latitude})) as distance " +
            "ORDER BY distance LIMIT 1 RETURN v")
    Vertex findClosestNodes(Double x, Double y);
}
