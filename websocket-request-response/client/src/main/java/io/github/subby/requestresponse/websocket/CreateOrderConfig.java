package io.github.subby.requestresponse.websocket;


import io.github.subby.requestresponse.websocket.model.Order;
import io.github.subby.requestresponse.websocket.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.net.URI;
import java.util.List;

@Slf4j
@Configuration
public class CreateOrderConfig {

    @Bean
    public ApplicationRunner applicationRunner(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            var requester = requesterBuilder.websocket(URI.create("ws://localhost:8080/orders"));

            requester
                    .route("createOrder")
                    .data(newOrder())
                    .retrieveMono(Order.class)
                    .subscribe(response -> log.info("Received order create response: {}", response));
        };
    }

    private Order newOrder() {
        return new Order(
                "John Cena",
                List.of(new Product("Battery"), new Product("TV"))
        );
    }

}
