package io.github.subby.channel.server.model;

import java.util.List;

public record Fulfilment(String orderNumber, String type, List<Product> products) {

    public Fulfilment(String type, List<Product> products) {
        this(null, type, products);
    }

}
