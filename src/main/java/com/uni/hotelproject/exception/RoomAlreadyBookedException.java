package com.uni.hotelproject.exception;

public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
