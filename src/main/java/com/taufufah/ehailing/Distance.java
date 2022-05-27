package com.taufufah.ehailing;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Distance {
    @RelationshipId
    private Long id;

    private double distance;

    @TargetNode
    private Vertex vertex;

    public Distance(Vertex vertex1, Vertex vertex2) {
        this.vertex = vertex2;
        double x1 = vertex1.getLongitude();
        double y1 = vertex1.getLatitude();
        double x2 = vertex2.getLongitude();
        double y2 = vertex2.getLatitude();
        this.distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getDistance() {
        return distance;
    }
}
