package com.example.cdcapi.exceptions;

public class ObjectNotFindException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectNotFindException(String msg) {
        super(msg);
    }

    public ObjectNotFindException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
