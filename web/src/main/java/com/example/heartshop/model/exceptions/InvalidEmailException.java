package com.example.heartshop.model.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(){
        super("Invalid Email");
    }
}