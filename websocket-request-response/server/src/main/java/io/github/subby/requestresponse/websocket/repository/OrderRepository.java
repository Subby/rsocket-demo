package io.github.subby.requestresponse.websocket.repository;

import io.github.subby.requestresponse.websocket.model.Order;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static io.github.subby.requestresponse.websocket.util.Helper.buildOrderWithId;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class OrderRepository {


    public Mono<Order> saveOrder(Order order) {
        return Mono.just(new Order(
                        UUID.randomUUID().toString(),
                        order.customerName(),
                        order.products()));
    }

    public Mono<Order> fetchOrder(String id) {
        if(hasText(id)) {
            return Mono.just(
                    buildOrderWithId(id)
            );
        }
        return Mono.empty();
    }
}
