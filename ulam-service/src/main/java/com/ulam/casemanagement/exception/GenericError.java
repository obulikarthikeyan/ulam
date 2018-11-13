package com.ulam.casemanagement.exception;

import com.ulam.casemanagement.constants.ErrorType;
import lombok.Data;

@Data
public class GenericError {

    private String errorType;
    private String errorMsg;

    public GenericError(ErrorType errorType, String errorMsg) {
        this.errorType = errorType.name();
        this.errorMsg = errorMsg;
    }
}
