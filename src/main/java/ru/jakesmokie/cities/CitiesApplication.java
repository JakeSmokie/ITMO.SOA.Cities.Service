package ru.jakesmokie.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "second", configuration = SampleRibbonConfiguration.class)
@EnableFeignClients("ru.jakesmokie.cities.services")
public class CitiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitiesApplication.class, args);
    }

}
