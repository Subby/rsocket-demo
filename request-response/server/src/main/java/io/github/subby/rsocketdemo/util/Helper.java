package io.github.subby.rsocketdemo.util;

import io.github.subby.rsocketdemo.model.Order;
import io.github.subby.rsocketdemo.model.Product;

import java.util.List;

public class Helper {

    public static Order buildOrderWithId(String id) {
        return new Order(
                id,
                "John Cena",
                buildProducts()
                );
    }

    private static List<Product> buildProducts() {
        return List.of(new Product("Battery"), new Product("TV"));
    }
}
