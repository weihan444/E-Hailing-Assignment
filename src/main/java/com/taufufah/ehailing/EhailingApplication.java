package com.taufufah.ehailing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class EhailingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhailingApplication.class, args);

    }

    @Bean
    CommandLineRunner demo(Neo4jClient neo4jClient, VertexRepository vertexRepository,
            DriverRepository driverRepository,
            CustomerRepository customerRepository, UpdateService updateService) {
        return args -> {
            vertexRepository.deleteAll();
            driverRepository.deleteAll();
            customerRepository.deleteAll();
            Vertex a = new Vertex(10, 5);
            Vertex b = new Vertex(8, 15);
            Vertex c = new Vertex(13, 25);
            Vertex d = new Vertex(42, 34);

            a.connectesWith(b);
            a.connectesWith(c);
            d.connectesWith(b);
            c.connectesWith(b);

            Driver d1 = new Driver("Ali", 4, 23.0, 10.0);
            Driver d2 = new Driver("Bii", 4, 23.0, 10.0);
            Customer c1 = new Customer("Abu", LocalDateTime.now(), 4, 16.0, 16.0, 30.0, 30.0);
            Customer c2 = new Customer("Jack", LocalDateTime.now(), 4, 50.0, 32.0, 60.0, 30.0);
            d1.setCustomer(c1);
            d2.setCustomer(c2);

            vertexRepository.save(a);
            vertexRepository.save(b);
            vertexRepository.save(c);
            vertexRepository.save(d);
            driverRepository.save(d1);
            driverRepository.save(d2);
            customerRepository.save(c1);
            customerRepository.save(c2);

            List<Driver> activeDriver = driverRepository.findAllByStatus(Status.AVAILABLE);

            for (Driver driver : activeDriver) {
                if (driver.getVertex() == null) {
                    Vertex closest = vertexRepository.findClosestNodes(driver.getLongitude(), driver.getLatitidue());
                    if (closest != null) {
                        driverRepository.connectToClosestVertex(driver.getId(), closest.getId());
                    }
                }

                if (driver.getCustomer() != null && driver.getCustomer().getVertex() == null) {
                    Vertex closest = vertexRepository.findClosestNodes(driver.getCustomer().getCurr_longitude(),
                            driver.getCustomer().getCurr_longitude());
                    if (closest != null) {
                        customerRepository.connectToClosestVertex(driver.getCustomer().getId(), closest.getId());
                    }
                }
            }

            updateService.findShortestPath(activeDriver.get(0).getId(), activeDriver.get(0).getCustomer().getId());
        };
    }
}
