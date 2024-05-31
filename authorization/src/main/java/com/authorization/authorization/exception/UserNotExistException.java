package com.authorization.authorization.exception;

public class UserNotExistException extends Throwable {
    public UserNotExistException(String message) {
        super(message);
    }
}
