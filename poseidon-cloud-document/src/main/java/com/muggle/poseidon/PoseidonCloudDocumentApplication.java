package com.muggle.poseidon;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@EnableFeignClients
@EnableDistributedTransaction
public class PoseidonCloudDocumentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoseidonCloudDocumentApplication.class, args);
    }

}
