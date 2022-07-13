package io.github.subby.rsocketdemo;


import io.github.subby.rsocketdemo.model.Order;
import io.github.subby.rsocketdemo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.util.List;

@Slf4j
@Configuration
public class CreateOrderConfig {

    @Bean
    public ApplicationRunner applicationRunner(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            RSocketRequester requester = requesterBuilder.tcp("localhost", 7000);

            requester
                    .route("")
                    .data(new Order(
                            "John Cena",
                            List.of(new Product("Battery"), new Product("TV"))
                    ))
                    .retrieveMono(Order.class)
                    .subscribe(response -> log.info("Received reponse: {}", response));
        };
    }

}
