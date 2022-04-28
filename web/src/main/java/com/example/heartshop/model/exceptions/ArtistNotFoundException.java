package com.example.heartshop.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(Long id) { super(String.format("Artist not found"));}
}
