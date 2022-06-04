package com.taufufah.ehailing.exceptions;

public class NotEnoughCapacity extends RuntimeException {
    public NotEnoughCapacity(int driverCapacity, int customerCapacity) {
        super("Not enough capacity " + "driver: " + driverCapacity + ", customer: " + customerCapacity);
    }
}