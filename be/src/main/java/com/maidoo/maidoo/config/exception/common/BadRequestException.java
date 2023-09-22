package com.maidoo.maidoo.config.exception.common;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(){
        super();
    }

}
