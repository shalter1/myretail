package com.myretail.restservice;

import org.springframework.data.annotation.Id;

public class Price {

    @Id
    private Integer id;
    private double value;
    private String currency_code;

    public Price(Integer id, double value, String currency_code) {
        this.id = id;
        this.value = value;
        this.currency_code = currency_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
