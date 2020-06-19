package ru.fedoroffaa.regulator_interface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class RegulatorInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegulatorInterfaceApplication.class, args);
    }

}
