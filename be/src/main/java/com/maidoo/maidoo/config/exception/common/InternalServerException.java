package com.maidoo.maidoo.config.exception.common;

import com.maidoo.maidoo.config.exception.ErrorCode;
import com.maidoo.maidoo.config.exception.ServerException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends ServerException {
    public InternalServerException(ErrorCode errorCode, ErrorCode.Params... params) {
        super(errorCode, HttpStatus.INTERNAL_SERVER_ERROR, params);
    }
}
