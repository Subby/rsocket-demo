package io.github.subby.channel.client;


import io.github.subby.channel.client.model.Fulfilment;
import io.github.subby.channel.client.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class ProcessFulfilmentConfig {

    @Bean
    public ApplicationRunner searchOrderApplicationRunner(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            var requester = requesterBuilder.tcp("localhost", 7000);

            requester
                    .route("processFulfilments")
                    .data(createFulfilments())
                    .retrieveFlux(Fulfilment.class)
                    .subscribe(response -> log.info("Received process fulfilment response: {}", response));
        };
    }

    private Flux<Fulfilment> createFulfilments() {
        return Flux.fromArray(new Fulfilment[] {
                new Fulfilment("ADTU", List.of(new Product("Apron"), new Product("T-Shirt"))),
                new Fulfilment("ADSI", List.of(new Product("Battery"), new Product("Headphones"))),
                new Fulfilment("ADDO", List.of(new Product("Fridge"), new Product("Washing Machine")))
        }).delayElements(Duration.ofSeconds(3));
    }

}
