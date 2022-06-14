package com.taufufah.ehailing.controller;

import com.taufufah.ehailing.exceptions.CustomerNotFoundException;
import com.taufufah.ehailing.exceptions.DriverNotFoundException;
import com.taufufah.ehailing.exceptions.NotEnoughCapacity;
import com.taufufah.ehailing.model.Customer;
import com.taufufah.ehailing.model.Driver;
import com.taufufah.ehailing.model.Status;
import com.taufufah.ehailing.model.Vertex;
import com.taufufah.ehailing.repository.CustomerRepository;
import com.taufufah.ehailing.repository.DriverRepository;
import com.taufufah.ehailing.repository.VertexRepository;
import com.taufufah.ehailing.service.UpdateService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    private final DriverRepository driverRepository;
    private final CustomerRepository customerRepository;
    private final VertexRepository vertexRepository;
    private final UpdateService updateService;

    public DriverController(DriverRepository driverRepository, VertexRepository vertexRepository,
            CustomerRepository customerRepository, UpdateService updateService) {
        this.driverRepository = driverRepository;
        this.vertexRepository = vertexRepository;
        this.customerRepository = customerRepository;
        this.updateService = updateService;
    }

    @GetMapping("/drivers")
    List<Driver> getAllDrivers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return driverRepository.findOneByName(name);
        }
        return driverRepository.findAll();
    }

    @GetMapping("/drivers/{id}")
    Driver getOneDriver(@PathVariable Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
    }

    @GetMapping("/drivers/status/{status}")
    List<Driver> getDriverbyStatus(@PathVariable String status) {
        return driverRepository.findAllByStatus(Status.valueOf(status.toUpperCase()));
    }

    @PostMapping("/drivers")
    Driver newDriver(@RequestBody Driver newDriver) {
        Long driverId = driverRepository.save(newDriver).getId();
        Vertex closest = vertexRepository.findClosestNodes(newDriver.getLongitude(), newDriver.getLatitude());
        return driverRepository.connectToClosestVertex(driverId, closest.getId());
    }

    @PutMapping("/drivers/{id}/updateLocation")
    Driver updateDriverLocation(@RequestBody Driver driver, @PathVariable Long id) {
        return driverRepository.updateDriverLocation(id, driver.getLongitude(), driver.getLatitude());
    }

    @PutMapping("/drivers/{id}/updateStatus")
    Driver updateDriverStatus(@RequestBody Driver driver, @PathVariable Long id) {
        return driverRepository.updateDriverStatus(id, driver.getStatus());
    }

    @PostMapping("/drivers/{driverId}/fetch/{customerId}")
    Driver fetchCustomer(@PathVariable Long driverId, @PathVariable Long customerId) {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException(driverId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        if (driver.getCapacity() < customer.getCapacity()) {
            throw new NotEnoughCapacity(driver.getCapacity(), customer.getCapacity());
        }

        driverRepository.deleteFetching(driverId);
        Driver responseDriver = driverRepository.fetchCustomer(driverId, customerId);
        updateService.findShortestPath(driverId, customerId);
        return responseDriver;
    }

    @DeleteMapping("/drivers/{id}")
    void deleteDriver(@PathVariable Long id) {
        driverRepository.deleteById(id);
    }

    @DeleteMapping("/drivers/{id}/removeFetching")
    Driver removeFetching(@PathVariable Long id) {
        return driverRepository.deleteFetching(id);
    }

    @DeleteMapping("/drivers/{id}/removeVertex")
    Driver deleteConnectedVertex(@PathVariable Long id) {
        return driverRepository.deleteConnectedVertex(id);
    }
}
