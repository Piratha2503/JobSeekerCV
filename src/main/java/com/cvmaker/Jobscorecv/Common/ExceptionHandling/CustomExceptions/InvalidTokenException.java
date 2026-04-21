package com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String message) {
        super(message);
    }
}
