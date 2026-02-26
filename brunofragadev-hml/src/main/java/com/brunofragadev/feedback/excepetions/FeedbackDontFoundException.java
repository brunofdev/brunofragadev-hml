package com.brunofragadev.feedback.excepetions;

public class FeedbackDontFoundException extends RuntimeException{
    public FeedbackDontFoundException(String message){
        super(message);
    }
}
