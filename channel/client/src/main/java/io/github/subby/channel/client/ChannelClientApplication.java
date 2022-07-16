package io.github.subby.channel.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChannelClientApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ChannelClientApplication.class, args);
        Thread.sleep(15000);
    }

}
