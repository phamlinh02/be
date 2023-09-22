package com.maidoo.maidoo.config.exception;


import com.maidoo.maidoo.config.exception.common.ClientCauseException;

public class ErrorParameterException extends ClientCauseException {
    public ErrorParameterException() {
        super(ErrorCode.ERROR_PARAMETER);
    }
}
