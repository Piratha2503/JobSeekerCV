package com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions;

public class InsufficientTokenException extends RuntimeException{
    public InsufficientTokenException(String message){
        super(message);
    }
}
