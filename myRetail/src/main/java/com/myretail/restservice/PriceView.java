package com.myretail.restservice;

/*
* The purpose of this class is to act as an intermediary between the client
* and the actual db class. Primarily, it strips off the id so that clients never have to deal
* with multiple id fields.
*
* */
public class PriceView {

    private double value;
    private String currency_code;

    public PriceView() {
    }

    public PriceView(double value, String currency_code) {
        this.value = value;
        this.currency_code = currency_code;
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
