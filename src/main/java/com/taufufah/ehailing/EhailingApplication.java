package com.taufufah.ehailing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EhailingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhailingApplication.class, args);

    }

    @Bean
    CommandLineRunner demo(VertexRepository vertexRepository) {
        return args -> {
            vertexRepository.deleteAll();

            Vertex a = new Vertex(10, 5);
            Vertex b = new Vertex(8, 15);
            Vertex c = new Vertex(13, 25);
            Vertex d = new Vertex(42, 34);

            a.connectesWith(b);
            a.connectesWith(c);
            d.connectesWith(b);
            c.connectesWith(b);

            vertexRepository.save(a);
            vertexRepository.save(b);
            vertexRepository.save(c);
            vertexRepository.save(d);
        };
    }
}
