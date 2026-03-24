package com.brunofragadev.core.user.exception;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException (String message){
        super(message);
    }
}
