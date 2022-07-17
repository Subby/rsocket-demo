package io.github.subby.requestresponse.websocket.controller;

import io.github.subby.requestresponse.websocket.model.Order;
import io.github.subby.requestresponse.websocket.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderRequestResponseController {

    private final OrderRepository orderRepository;

    @MessageMapping("createOrder")
    public Mono<Order> handleOrderCreation(Mono<Order> orderMono) {
        return orderMono
                .doOnNext(order -> log.info("Received order request: {}", order))
                .flatMap(orderRepository::saveOrder);
    }

}
