package com.brunofragadev.core.feedback.excepetion;

public class FeedbackDontFoundException extends RuntimeException{
    public FeedbackDontFoundException(String message){
        super(message);
    }
}
