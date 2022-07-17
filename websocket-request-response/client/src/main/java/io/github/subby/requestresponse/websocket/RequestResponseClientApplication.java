package io.github.subby.requestresponse.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequestResponseClientApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RequestResponseClientApplication.class, args);
        Thread.sleep(1000);
    }

}
