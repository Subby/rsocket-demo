package io.github.subby.rsocketdemo.controller;

import io.github.subby.rsocketdemo.model.Order;
import io.github.subby.rsocketdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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

    @MessageMapping("getOrder/{id}")
    public Mono<Order> handleOrderFetching(@DestinationVariable("id") String id) {
        return orderRepository.fetchOrder(id);
    }

}
