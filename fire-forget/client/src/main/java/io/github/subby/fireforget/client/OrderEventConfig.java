package io.github.subby.fireforget.client;


import io.github.subby.fireforget.client.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Slf4j
@Configuration
public class OrderEventConfig {

    @Bean
    public ApplicationRunner orderEventApplicationRunner(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            var requester = requesterBuilder.tcp("localhost", 7000);

            requester
                    .route("receiveEvent")
                    .data(new OrderEvent("420", "ORDER_REQUESTED"))
                    .send()
                    .subscribe();
        };
    }

}
