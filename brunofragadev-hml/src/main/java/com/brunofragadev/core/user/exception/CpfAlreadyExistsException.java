package com.brunofragadev.core.user.exception;

public class CpfAlreadyExistsException extends RuntimeException{
    public CpfAlreadyExistsException(String message){
        super(message);
    }
}
