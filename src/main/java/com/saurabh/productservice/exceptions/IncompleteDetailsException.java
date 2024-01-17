package com.saurabh.productservice.exceptions;

public class IncompleteDetailsException extends RuntimeException{
    public IncompleteDetailsException(String message){
        super(message);
    }
}
