package com.taufufah.ehailing.dto;

public class DriverCustomerDto {
    private Long driverId;
    private Long customerId;

    public DriverCustomerDto(Long driverId, Long customerId) {
        this.driverId = driverId;
        this.customerId = customerId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
