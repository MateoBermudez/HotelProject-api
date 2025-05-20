package com.uni.hotelproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRoomNotFoundException(RoomNotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorMessage;
        errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(404)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("ROOM_NOT_FOUND")
                .build();

        return ResponseEntity.status(404).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message("An unexpected error occurred")
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(500)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("INTERNAL_SERVER_ERROR")
                .build();

        return ResponseEntity.status(500).body(errorMessage);
    }
}
