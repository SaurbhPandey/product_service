package com.saurabh.productservice.dtos;

import org.springframework.http.HttpStatus;

public class GenericResponseHandler<T> {
    private String message;
    private HttpStatus httpStatus;
    private T data;
}
