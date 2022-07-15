package io.github.subby.fireforget.server.controller;

import io.github.subby.fireforget.server.model.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderEventFireForgetController {

    @MessageMapping("receiveEvent")
    public Mono<Void> handleOrderFetching(Mono<OrderEvent> orderEvent) {
        return orderEvent
                .doOnNext(event -> log.info("Order event received: {}", event))
                .thenEmpty(Mono.empty());
    }

}
