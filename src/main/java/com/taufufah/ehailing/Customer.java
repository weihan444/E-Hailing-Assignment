package com.taufufah.ehailing;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class Customer {
    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    private Status status;

    private LocalDateTime expected_arrival_time;

    private Integer capacity;

    private Double longitude;

    private Double latitude;

    private Double dest_longitude;

    private Double dest_latitude;

    @Relationship(type = "CONNECTED_VERTEX")
    private Vertex vertex;

    @Relationship(type = "GOING_TO")
    private Destination destination;

    public Customer(String name, LocalDateTime expected_arrival_time, Integer capacity, Double longitude,
            Double latitude, Double dest_longitude, Double dest_latitude) {
        this.name = name;
        this.status = Status.PENDING;
        this.expected_arrival_time = expected_arrival_time;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dest_longitude = dest_longitude;
        this.dest_latitude = dest_latitude;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getExpected_arrival_time() {
        return expected_arrival_time;
    }

    public void setExpected_arrival_time(LocalDateTime expected_arrival_time) {
        this.expected_arrival_time = expected_arrival_time;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getDest_longitude() {
        return dest_longitude;
    }

    public void setDest_longitude(Double dest_longitude) {
        this.dest_longitude = dest_longitude;
    }

    public Double getDest_latitude() {
        return dest_latitude;
    }

    public void setDest_latitude(Double dest_latitude) {
        this.dest_latitude = dest_latitude;
    }

}
