package com.muggle;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class PoseidonCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoseidonCloudAdminApplication.class, args);
    }

}
