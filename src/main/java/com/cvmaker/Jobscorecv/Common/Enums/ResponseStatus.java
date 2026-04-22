package com.cvmaker.Jobscorecv.Common.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("Success"),
    FAILURE("Failure"),
    ERROR("Error"),
    UNKNOWN("Unknown"),
    DUPLICATE("Duplicate"),
    NOT_FOUND("Not Found");
    private final String status;

    public static ResponseStatus getByStatus(String status){

        for (ResponseStatus responseStatus : values()){
            if (status.equals(responseStatus.getStatus())) return responseStatus;
        }
        throw new AssertionError("Request status not found for given status [status: " + status + "]");
    }

}
