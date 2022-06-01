package com.taufufah.ehailing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Relationship;

public class Destination {
    @Id
    @GeneratedValue
    private Long id;

    private double longitude;

    private double latitude;

    public Destination(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Relationship(type = "CONNECTED_VERTEX")
    private Vertex vertex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }
}
