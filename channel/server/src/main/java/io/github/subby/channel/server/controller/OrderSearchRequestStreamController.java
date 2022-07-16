package io.github.subby.channel.server.controller;

import io.github.subby.channel.server.model.Fulfilment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderSearchRequestStreamController {

    @MessageMapping("processFulfilments")
    public Flux<Fulfilment> handleOrderFetching(Flux<Fulfilment> fulfilments) {
        return fulfilments
                .doOnNext(fulfilment -> log.info("Processing fulfilment: {}", fulfilment))
                .map(fulfilment -> new Fulfilment(UUID.randomUUID().toString(), fulfilment.type(), fulfilment.products()));
    }

}
