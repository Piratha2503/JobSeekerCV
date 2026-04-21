package com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions;

public class UserStatusException extends RuntimeException{
    public UserStatusException(String message){
        super(message);
    }
}
