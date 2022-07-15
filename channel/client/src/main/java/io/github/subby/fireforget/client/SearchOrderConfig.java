package io.github.subby.fireforget.client;


import io.github.subby.fireforget.client.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Slf4j
@Configuration
public class SearchOrderConfig {

    @Bean
    public ApplicationRunner searchOrderApplicationRunner(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            var requester = requesterBuilder.tcp("localhost", 7000);

            requester
                    .route("searchOrder/{customerName}", "John Cena")
                    .retrieveFlux(Order.class)
                    .subscribe(response -> log.info("Received order search response: {}", response));
        };
    }

}
