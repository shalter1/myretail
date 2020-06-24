package com.myretail.restservice.exception;

public class ProductTitleMissingException extends RuntimeException {

    public ProductTitleMissingException(String message) {
      super(message);
    }

    private static final long serialVersionUID = 1L;
}