package io.github.subby.fireforget.server.util;

import io.github.subby.fireforget.server.model.Product;
import io.github.subby.fireforget.server.model.Order;

import java.util.List;

public class Helper {

    public static Order buildOrder(String id) {
        return new Order(
                id,
                "John Cena",
                buildProducts()
                );
    }

    public static Order buildOrderOther(String id) {
        return new Order(
                id,
                "John Cena",
                buildProductsOther()
        );
    }

    private static List<Product> buildProducts() {
        return List.of(new Product("Battery"), new Product("TV"));
    }

    private static List<Product> buildProductsOther() {
        return List.of(new Product("Washing Machine"), new Product("Digitakt"));
    }
}
