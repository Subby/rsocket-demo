package io.github.subby.rsocketdemo.model;

import java.util.List;

public record Order (String orderNumber, String customerName, List<Product> products) {

    public Order(String customerName, List<Product> products) {
        this(null, customerName, products);
    }

}
