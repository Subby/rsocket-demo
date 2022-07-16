package io.github.subby.channel.client.model;

import java.util.List;

public record Fulfilment(String orderNumber, String type, List<Product> products) {

    public Fulfilment(String type, List<Product> products) {
        this(null, type, products);
    }

}
