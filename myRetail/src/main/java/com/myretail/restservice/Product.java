package com.myretail.restservice;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    private final Integer id;
    private final String name;
    @JsonProperty("current_price")
    private final PriceView price;

    public Product(Integer id, String name, PriceView price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PriceView getPrice() {
        return price;
    }
}
