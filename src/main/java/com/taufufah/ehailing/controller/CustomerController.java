package com.taufufah.ehailing.controller;

import java.util.List;

import com.taufufah.ehailing.exceptions.CustomerNotFoundException;
import com.taufufah.ehailing.model.Customer;
import com.taufufah.ehailing.model.Destination;
import com.taufufah.ehailing.model.Status;
import com.taufufah.ehailing.model.Vertex;
import com.taufufah.ehailing.repository.CustomerRepository;
import com.taufufah.ehailing.repository.DestinationRepository;
import com.taufufah.ehailing.repository.VertexRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final VertexRepository vertexRepository;
    private final DestinationRepository destinationRepository;

    public CustomerController(CustomerRepository customerRepository, VertexRepository vertexRepository,
            DestinationRepository destinationRepository) {
        this.customerRepository = customerRepository;
        this.vertexRepository = vertexRepository;
        this.destinationRepository = destinationRepository;
    }

    @GetMapping("/customers")
    List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    Customer getOneDriver(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @GetMapping("/customers/status/{status}")
    List<Customer> getDriverbyStatus(@PathVariable String status) {
        return customerRepository.findAllByStatus(Status.valueOf(status.toUpperCase()));
    }

    @PutMapping("/customers/{id}/updateStatus")
    Customer updateDriverStatus(@RequestBody Customer customer, @PathVariable Long id) {
        return customerRepository.updateCustomerStatus(id, customer.getStatus());
    }

    @PostMapping("/customers")
    Customer newDriver(@RequestBody Customer newCustomer) {
        Long customerId = customerRepository.save(newCustomer).getId();
        Vertex closestToCustomer = vertexRepository.findClosestNodes(newCustomer.getLongitude(),
                newCustomer.getLatitude());

        Destination destination = new Destination(newCustomer.getDest_longitude(), newCustomer.getDest_latitude());
        Long destId = destinationRepository.save(destination).getId();
        Vertex closestToDestination = vertexRepository.findClosestNodes(destination.getLongitude(),
                destination.getLatitude());

        destinationRepository.connectToClosestVertex(destId, closestToDestination.getId());
        return customerRepository.connectToClosestVertex(customerId, closestToCustomer.getId());
    }

}
