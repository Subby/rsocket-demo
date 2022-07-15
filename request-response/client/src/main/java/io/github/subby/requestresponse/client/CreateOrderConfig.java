package io.github.subby.requestresponse.client;


import io.github.subby.requestresponse.client.model.Order;
import io.github.subby.requestresponse.client.model.Product;
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
            var requester = requesterBuilder.tcp("localhost", 7000);

            requester
                    .route("createOrder")
                    .data(newOrder())
                    .retrieveMono(Order.class)
                    .subscribe(response -> log.info("Received order create response: {}", response));

            requester
                    .route("getOrder/{id}", "2")
                    .retrieveMono(Order.class)
                    .subscribe(response -> log.info("Received order get response: {}", response));
        };
    }

    private Order newOrder() {
        return new Order(
                "John Cena",
                List.of(new Product("Battery"), new Product("TV"))
        );
    }

}
