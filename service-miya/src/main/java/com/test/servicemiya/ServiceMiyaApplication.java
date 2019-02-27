package com.test.servicemiya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class ServiceMiyaApplication {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = Logger.getLogger(ServiceMiyaApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ServiceMiyaApplication.class, args);
    }

    @RequestMapping("/hi")
    public String callHome(){
        logger.log(Level.INFO, "calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:8988/miya", String.class);
    }

    @RequestMapping("/info")
    public String info(){
        logger.log(Level.INFO, "calling trace service-hi ");
        return "i'm service-hi";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
