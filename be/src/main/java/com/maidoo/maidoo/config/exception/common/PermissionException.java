package com.maidoo.maidoo.config.exception.common;

import com.maidoo.maidoo.config.exception.ErrorCode;
import com.maidoo.maidoo.config.exception.ServerException;
import org.springframework.http.HttpStatus;

public class PermissionException extends ServerException {
    public PermissionException(ErrorCode errorCode, ErrorCode.Params... params) {
        super(errorCode, HttpStatus.UNAUTHORIZED, params);
    }
}
