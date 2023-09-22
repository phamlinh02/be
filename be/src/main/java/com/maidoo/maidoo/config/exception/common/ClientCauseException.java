package com.maidoo.maidoo.config.exception.common;

import com.maidoo.maidoo.config.exception.ErrorCode;
import com.maidoo.maidoo.config.exception.ServerException;
import org.springframework.http.HttpStatus;

public class ClientCauseException extends ServerException {
    public ClientCauseException(ErrorCode errorCode, ErrorCode.Params... params) {
        super(errorCode, HttpStatus.BAD_REQUEST, params);
    }

}
