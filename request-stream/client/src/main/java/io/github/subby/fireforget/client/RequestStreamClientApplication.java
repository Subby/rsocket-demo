package io.github.subby.fireforget.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequestStreamClientApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RequestStreamClientApplication.class, args);
        Thread.sleep(15000);
    }

}
