package io.github.subby.fireforget.server.controller;

import io.github.subby.fireforget.server.util.Helper;
import io.github.subby.fireforget.server.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderSearchRequestStreamController {

    @MessageMapping("searchOrder/{customerName}")
    public Flux<Order> handleOrderFetching(@DestinationVariable("customerName") String customerName) {
        log.info("Order search request received for customer: {}", customerName);
        return Flux
                .interval(Duration.ofSeconds(3))
                .map(i -> {
                    //Send different order depending on second passed in
                    if(i % 2 == 0) {
                        return Helper.buildOrder(String.valueOf(i));
                    } else {
                        return Helper.buildOrderOther(String.valueOf(i));
                    }
                });
    }

}
