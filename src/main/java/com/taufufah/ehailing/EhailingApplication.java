package com.taufufah.ehailing;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EhailingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhailingApplication.class, args);

    }

    @Bean
    CommandLineRunner demo(Neo4jClient neo4jClient, VertexRepository vertexRepository,
            DriverRepository driverRepository,
            CustomerRepository customerRepository, DestinationRepository destinationRepository,
            UpdateService updateService) {
        return args -> {
            vertexRepository.deleteAll();
            driverRepository.deleteAll();
            customerRepository.deleteAll();
            destinationRepository.deleteAll();
            Vertex a = new Vertex(10, 5);
            Vertex b = new Vertex(8, 15);
            Vertex c = new Vertex(13, 25);
            Vertex d = new Vertex(42, 34);

            a.connectesWith(b);
            a.connectesWith(c);
            d.connectesWith(b);
            c.connectesWith(b);

            Driver d1 = new Driver("Ali", 4, 23.0, 10.0);
            Driver d2 = new Driver("Bii", 4, 30.0, 15.0);
            Customer c1 = new Customer("Abu", LocalDateTime.now(), 4, 10.0, 10.0, 10.0, 10.0);
            Customer c2 = new Customer("Jack", LocalDateTime.now(), 4, 50.0, 32.0, 10.0, 30.0);
            Destination dest1 = new Destination(c1.getDest_longitude(), c1.getDest_latitude());
            Destination dest2 = new Destination(c2.getDest_longitude(), c2.getDest_latitude());

            vertexRepository.save(a);
            vertexRepository.save(b);
            vertexRepository.save(c);
            vertexRepository.save(d);

            driverRepository.save(d1);
            driverRepository.save(d2);
            customerRepository.save(c1);
            customerRepository.save(c2);
            destinationRepository.save(dest1);
            destinationRepository.save(dest2);

            // C1 and C2 choose destination
            customerRepository.updateDestination(c1.getId(), dest1.getId());
            customerRepository.updateDestination(c2.getId(), dest2.getId());

            // D1 Fetch C1
            destinationRepository.connectToClosestVertex(dest1.getId(),
                    vertexRepository.findClosestNodes(dest1.getLongitude(), dest1.getLatitude()).getId());

            driverRepository.connectToClosestVertex(d1.getId(),
                    vertexRepository.findClosestNodes(d1.getLongitude(), d1.getLatitude()).getId());

            customerRepository.connectToClosestVertex(c1.getId(),
                    vertexRepository
                            .findClosestNodes(c1.getLongitude(), c1.getLatitude())
                            .getId());

            driverRepository.fetchCustomer(d1.getId(), c1.getId());
            driverRepository.updateDriverStatus(d1.getId(), Status.NOT_AVAILABLE);
            customerRepository.updateCustomerStatus(c1.getId(), Status.PENDING);

            // D2 Fetch C2
            destinationRepository.connectToClosestVertex(dest2.getId(),
                    vertexRepository.findClosestNodes(dest2.getLongitude(), dest2.getLatitude()).getId());

            driverRepository.connectToClosestVertex(d2.getId(),
                    vertexRepository.findClosestNodes(d2.getLongitude(), d2.getLatitude()).getId());

            customerRepository.connectToClosestVertex(c2.getId(),
                    vertexRepository
                            .findClosestNodes(c2.getLongitude(), c2.getLatitude())
                            .getId());

            driverRepository.fetchCustomer(d2.getId(), c2.getId());
            driverRepository.updateDriverStatus(d2.getId(), Status.NOT_AVAILABLE);
            customerRepository.updateCustomerStatus(d2.getId(), Status.PENDING);

            updateService.findShortestPath(d1.getId(), c1.getId());
            updateService.findShortestPath(d2.getId(), c2.getId());
        };
    }
}
