package com.example.heartshop.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException  extends  RuntimeException{

    public OrderNotFoundException(Long id) {
        super(String.format("Order with id: %d not found!", id));
    }
}
