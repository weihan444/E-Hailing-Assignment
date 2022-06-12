package com.taufufah.ehailing;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.taufufah.ehailing.model.Customer;
import com.taufufah.ehailing.model.Destination;
import com.taufufah.ehailing.model.Driver;
import com.taufufah.ehailing.model.Status;
import com.taufufah.ehailing.model.Vertex;
import com.taufufah.ehailing.repository.CustomerRepository;
import com.taufufah.ehailing.repository.DestinationRepository;
import com.taufufah.ehailing.repository.DriverRepository;
import com.taufufah.ehailing.repository.VertexRepository;
import com.taufufah.ehailing.service.UpdateService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
            vertexRepository.save(new Vertex(72, 130));
            vertexRepository.save(new Vertex(71, 124));
            vertexRepository.save(new Vertex(82, 113));
            vertexRepository.save(new Vertex(93, 105));
            vertexRepository.save(new Vertex(95, 102));
            vertexRepository.save(new Vertex(105, 102));
            vertexRepository.save(new Vertex(110, 105));
            vertexRepository.save(new Vertex(122, 106));
            vertexRepository.save(new Vertex(121, 114));
            vertexRepository.save(new Vertex(121, 119));

            vertexRepository.save(new Vertex(76, 131));
            vertexRepository.save(new Vertex(77, 126));
            vertexRepository.save(new Vertex(85, 117));
            vertexRepository.save(new Vertex(77, 135));
            vertexRepository.save(new Vertex(82, 138));
            vertexRepository.save(new Vertex(83, 141));
            vertexRepository.save(new Vertex(97, 116));
            vertexRepository.save(new Vertex(108, 121));
            vertexRepository.save(new Vertex(119, 126));
            vertexRepository.save(new Vertex(123, 128));
            vertexRepository.save(new Vertex(128, 130));
            vertexRepository.save(new Vertex(130, 131));
            vertexRepository.save(new Vertex(92, 141));
            vertexRepository.save(new Vertex(102, 139));
            vertexRepository.save(new Vertex(111, 136));
            vertexRepository.save(new Vertex(118, 134));
            vertexRepository.save(new Vertex(126, 134));

            vertexRepository.connectVertex(76, 131, 72, 130);
            vertexRepository.connectVertex(72, 130, 71, 124);
            vertexRepository.connectVertex(71, 124, 82, 113);
            vertexRepository.connectVertex(82, 113, 93, 105);
            vertexRepository.connectVertex(93, 105, 95, 102);
            vertexRepository.connectVertex(95, 102, 105, 102);
            vertexRepository.connectVertex(105, 102, 110, 105);
            vertexRepository.connectVertex(110, 105, 122, 106);
            vertexRepository.connectVertex(122, 106, 121, 114);
            vertexRepository.connectVertex(121, 114, 121, 119);
            vertexRepository.connectVertex(121, 119, 119, 126);

            vertexRepository.connectVertex(76, 131, 77, 126);
            vertexRepository.connectVertex(77, 126, 85, 117);
            vertexRepository.connectVertex(85, 117, 97, 116);
            vertexRepository.connectVertex(97, 116, 108, 121);
            vertexRepository.connectVertex(108, 121, 119, 126);

            vertexRepository.connectVertex(76, 131, 77, 135);
            vertexRepository.connectVertex(77, 135, 82, 138);
            vertexRepository.connectVertex(82, 138, 83, 141);

            vertexRepository.connectVertex(119, 126, 123, 128);
            vertexRepository.connectVertex(123, 128, 128, 130);
            vertexRepository.connectVertex(128, 130, 130, 131);

            vertexRepository.connectVertex(83, 141, 92, 141);
            vertexRepository.connectVertex(92, 141, 102, 139);
            vertexRepository.connectVertex(102, 139, 111, 136);
            vertexRepository.connectVertex(111, 136, 118, 134);
            vertexRepository.connectVertex(118, 134, 126, 134);
            vertexRepository.connectVertex(126, 134, 130, 131);

        };
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list of allowed origins
        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    // @Bean
    // CommandLineRunner demo(Neo4jClient neo4jClient, VertexRepository
    // vertexRepository,
    // DriverRepository driverRepository,
    // CustomerRepository customerRepository, DestinationRepository
    // destinationRepository,
    // UpdateService updateService) {
    // return args -> {
    // vertexRepository.deleteAll();
    // driverRepository.deleteAll();
    // customerRepository.deleteAll();
    // destinationRepository.deleteAll();
    // Vertex a = new Vertex(10, 5);
    // Vertex b = new Vertex(8, 15);
    // Vertex c = new Vertex(13, 25);
    // Vertex d = new Vertex(42, 34);

    // a.connectesWith(b);
    // a.connectesWith(c);
    // d.connectesWith(b);
    // c.connectesWith(b);

    // Driver d1 = new Driver("Ali", 4, 23.0, 10.0);
    // Driver d2 = new Driver("Bii", 4, 30.0, 15.0);
    // Customer c1 = new Customer("Abu", LocalDateTime.now(), 4, 10.0, 10.0, 16.0,
    // 10.0);
    // Customer c2 = new Customer("Jack", LocalDateTime.now(), 4, 50.0, 32.0, 10.0,
    // 30.0);
    // Destination dest1 = new Destination(c1.getDest_longitude(),
    // c1.getDest_latitude());
    // Destination dest2 = new Destination(c2.getDest_longitude(),
    // c2.getDest_latitude());

    // vertexRepository.save(a);
    // vertexRepository.save(b);
    // vertexRepository.save(c);
    // vertexRepository.save(d);

    // driverRepository.save(d1);
    // driverRepository.save(d2);
    // customerRepository.save(c1);
    // customerRepository.save(c2);
    // destinationRepository.save(dest1);
    // destinationRepository.save(dest2);

    // // C1 and C2 choose destination
    // customerRepository.updateDestination(c1.getId(), dest1.getId());
    // customerRepository.updateDestination(c2.getId(), dest2.getId());

    // // D1 Fetch C1
    // destinationRepository.connectToClosestVertex(dest1.getId(),
    // vertexRepository.findClosestNodes(dest1.getLongitude(),
    // dest1.getLatitude()).getId());

    // driverRepository.connectToClosestVertex(d1.getId(),
    // vertexRepository.findClosestNodes(d1.getLongitude(),
    // d1.getLatitude()).getId());

    // customerRepository.connectToClosestVertex(c1.getId(),
    // vertexRepository
    // .findClosestNodes(c1.getLongitude(), c1.getLatitude())
    // .getId());

    // driverRepository.fetchCustomer(d1.getId(), c1.getId());
    // driverRepository.updateDriverStatus(d1.getId(), Status.NOT_AVAILABLE);
    // customerRepository.updateCustomerStatus(c1.getId(), Status.PENDING);

    // // D2 Fetch C2
    // destinationRepository.connectToClosestVertex(dest2.getId(),
    // vertexRepository.findClosestNodes(dest2.getLongitude(),
    // dest2.getLatitude()).getId());

    // driverRepository.connectToClosestVertex(d2.getId(),
    // vertexRepository.findClosestNodes(d2.getLongitude(),
    // d2.getLatitude()).getId());

    // customerRepository.connectToClosestVertex(c2.getId(),
    // vertexRepository
    // .findClosestNodes(c2.getLongitude(), c2.getLatitude())
    // .getId());

    // driverRepository.fetchCustomer(d2.getId(), c2.getId());
    // driverRepository.updateDriverStatus(d2.getId(), Status.NOT_AVAILABLE);
    // customerRepository.updateCustomerStatus(d2.getId(), Status.PENDING);

    // updateService.findShortestPath(d1.getId(), c1.getId());
    // updateService.findShortestPath(d2.getId(), c2.getId());
    // };
    // }
}
