package io.github.subby.fireforget.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FireForgetClientApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FireForgetClientApplication.class, args);
        Thread.sleep(1000);
    }

}
