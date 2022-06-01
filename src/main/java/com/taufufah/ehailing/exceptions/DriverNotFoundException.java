package com.taufufah.ehailing.exceptions;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException(Long id) {
        super("Could not find driver " + id);
    }
}