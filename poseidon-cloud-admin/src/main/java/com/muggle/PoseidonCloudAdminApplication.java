package com.muggle;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableAdminServer
@ServletComponentScan
public class PoseidonCloudAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoseidonCloudAdminApplication.class, args);
    }

}
