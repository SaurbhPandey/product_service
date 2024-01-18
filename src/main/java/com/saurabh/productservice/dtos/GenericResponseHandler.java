package com.saurabh.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GenericResponseHandler<T> {
    private String message;
    private HttpStatus httpStatus;
    private T data;
}
