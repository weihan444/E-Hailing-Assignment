package com.taufufah.ehailing.model;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Distance {
    @RelationshipId
    private Long id;

    private double distance;

    @TargetNode
    private Vertex nextVertex;

    public Distance(double distance, Vertex nextVertex) {
        this.nextVertex = nextVertex;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }
}
