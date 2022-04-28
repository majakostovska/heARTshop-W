package com.example.heartshop.model.exceptions;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException()
    {
        super("Invalid user credentials exception");
    }
}
