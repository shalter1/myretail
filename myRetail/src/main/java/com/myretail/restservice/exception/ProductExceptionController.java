package com.myretail.restservice.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception) {
        String message = exception.getMessage();

        return new ResponseEntity<>("Product " + message + " not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductDataCorruptException.class)
    public ResponseEntity<Object> exception(ProductDataCorruptException exception) {
        String message = exception.getMessage();

        return new ResponseEntity<>("Product data corrupt", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ProductTitleMissingException.class)
    public ResponseEntity<Object> exception(ProductTitleMissingException exception) {
        String message = exception.getMessage();

        return new ResponseEntity<>("Product " + message + " name missing.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";

        return new ResponseEntity<>("Malformed JSON request: " + ((Throwable)ex).getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";

        return new ResponseEntity<>("Malformed JSON request: " + ((Throwable)ex).getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";

        return new ResponseEntity<>("Malformed JSON request: " + ((Throwable)ex).getMessage(), HttpStatus.BAD_REQUEST);
    }

}
