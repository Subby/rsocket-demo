package io.github.subby.requestresponse.server;

import io.github.subby.requestresponse.server.model.Order;
import io.github.subby.requestresponse.server.model.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RSocketClientToServerTest {

    private static RSocketRequester requester;

    @BeforeAll
    public static void setupOnce(@Autowired RSocketRequester.Builder builder, @Value("${spring.rsocket.server.port}") Integer port) {
        requester = builder
                .connectTcp("localhost", port)
                .block();
    }

    @Test
    public void testCreateOrder() {

        var order = new Order(
                "John Cena",
                List.of(new Product("Battery"), new Product("TV"))
        );
        var result = requester
                .route("createOrder")
                .data(order)
                .retrieveMono(Order.class);

        StepVerifier
                .create(result)
                .consumeNextWith(message -> {
                            assertThat(message.orderNumber()).isNotNull();
                            assertThat(message.customerName()).isEqualTo("John Cena");
                            assertThat(message.products().size()).isEqualTo(2);
                        }
                )
                .verifyComplete();
    }

    @AfterAll
    public static void tearDownOnce() {
        requester.rsocket().dispose();
    }

}
