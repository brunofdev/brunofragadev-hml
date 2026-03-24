package com.brunofragadev.core.user.exception;

public class UserDontHaveEmailRegistered extends RuntimeException{
    public UserDontHaveEmailRegistered(String message){
        super(message);
    }
}
