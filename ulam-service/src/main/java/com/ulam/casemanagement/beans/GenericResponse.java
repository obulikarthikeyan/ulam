package com.ulam.casemanagement.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulam.casemanagement.exception.GenericError;
import lombok.Data;

@Data
public class GenericResponse {

    private String message;
    
    @JsonProperty(value = "error")
    private GenericError genericError;

    public GenericResponse(String message) {
        this.message = message;
    }

    public GenericResponse(GenericError genericError) {
        this.genericError = genericError;
    }
}
