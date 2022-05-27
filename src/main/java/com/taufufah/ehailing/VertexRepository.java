package com.taufufah.ehailing;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VertexRepository extends Neo4jRepository<Vertex, Long> {

}
