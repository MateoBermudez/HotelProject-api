package com.uni.hotelproject.exception;

public class UserIDAlreadyExistsException extends RuntimeException {
    public UserIDAlreadyExistsException(String message) {
        super(message);
    }
}
