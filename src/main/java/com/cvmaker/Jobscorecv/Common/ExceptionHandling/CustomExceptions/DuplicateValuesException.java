package com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions;

public class DuplicateValuesException extends RuntimeException{

    public DuplicateValuesException(String message){
        super(message);
    }
}
