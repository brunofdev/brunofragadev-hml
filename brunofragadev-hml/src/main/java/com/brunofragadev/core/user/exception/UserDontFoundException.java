package com.brunofragadev.core.user.exception;

public class UserDontFoundException extends RuntimeException{
    public UserDontFoundException (String message){
        super(message);
    }
}
