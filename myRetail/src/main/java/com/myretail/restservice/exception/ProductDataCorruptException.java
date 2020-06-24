package com.myretail.restservice.exception;

public class ProductDataCorruptException extends RuntimeException {

    public ProductDataCorruptException(String message) {
      super(message);
    }

    private static final long serialVersionUID = 1L;
}