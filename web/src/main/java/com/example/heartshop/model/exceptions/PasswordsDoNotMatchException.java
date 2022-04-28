package com.example.heartshop.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("The passwords do not match!");
    }
}
