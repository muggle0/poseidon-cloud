package com.muggle.poseidon;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@EnableFeignClients
@EnableDistributedTransaction
public class PoseidonCloudUserApplication {
    /** logger */
    private static final Logger log = LoggerFactory.getLogger(PoseidonCloudUserApplication.class);

    public static void main(String[] args) {
        log.info("程序加载中 \uF06C\t\uF06C\t\uF06C\t\uF06C\t\uF06C\t\uF06C\t\uF06C\t\uF06C\t\uF06C\t");
        SpringApplication.run(PoseidonCloudUserApplication.class, args);
    }

}
