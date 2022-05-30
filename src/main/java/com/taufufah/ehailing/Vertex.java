package com.taufufah.ehailing;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Vertex {

    @Id
    @GeneratedValue
    private Long id;

    private double longitude;

    private double latitude;

    public Vertex(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Relationship(type = "CONNECTED_VERTEX")
    public Set<Distance> vertexAndDist;

    public void connectesWith(Vertex nextVertex) {
        if (vertexAndDist == null) {
            vertexAndDist = new HashSet<>();
        }
        double x1 = longitude;
        double y1 = latitude;
        double x2 = nextVertex.longitude;
        double y2 = nextVertex.latitude;
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        vertexAndDist.add(new Distance(distance, nextVertex));
    }

    public Long getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
