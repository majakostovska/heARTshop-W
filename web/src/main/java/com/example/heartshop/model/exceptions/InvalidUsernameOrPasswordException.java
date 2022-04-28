package com.example.heartshop.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException(){
        super("Your username or password is invalid.");
    }
}
