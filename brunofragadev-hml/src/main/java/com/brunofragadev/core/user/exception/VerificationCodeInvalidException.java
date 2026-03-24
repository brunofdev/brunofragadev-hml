package com.brunofragadev.core.user.exception;

public class VerificationCodeInvalidException extends RuntimeException{
    public VerificationCodeInvalidException (String message){
        super(message);
    }
}
