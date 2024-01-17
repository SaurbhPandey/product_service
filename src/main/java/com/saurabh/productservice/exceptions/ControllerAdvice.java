package com.saurabh.productservice.exceptions;

import com.saurabh.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
     public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
       ExceptionDto exceptionDto = new ExceptionDto();
       exceptionDto.setMessage(notFoundException.getMessage());
       exceptionDto.setStatusCode(HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(exceptionDto , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncompleteDetailsException.class)
    public ResponseEntity<ExceptionDto> handleIncompleteDetailsException(IncompleteDetailsException incompleteDetailsException){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionDto.setMessage(incompleteDetailsException.getMessage());
        return new ResponseEntity<>(exceptionDto , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
