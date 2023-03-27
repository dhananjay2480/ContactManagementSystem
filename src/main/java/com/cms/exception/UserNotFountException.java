package com.cms.exception;

public class UserNotFountException extends AppException{
    private static final long serialVersionUID = 1L;
    public UserNotFountException(String errorType, String errorCode, String errorMessage) {
        super(errorType, errorCode, errorMessage);
    }
}
