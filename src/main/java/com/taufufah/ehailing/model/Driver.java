package com.taufufah.ehailing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Driver {
    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    private Status status;

    private Integer capacity;

    private Double longitude;

    private Double latitude;

    @Relationship(type = "FETCHING")
    private Customer customer;

    @Relationship(type = "CONNECTED_VERTEX")
    private Vertex vertex;

    public Driver(String name, Integer capacity, Double longitude, Double latitude) {
        this.name = name;
        this.status = status.AVAILABLE;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return Id;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setLatitidue(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
