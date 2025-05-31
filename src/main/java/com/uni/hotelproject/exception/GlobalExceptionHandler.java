package com.uni.hotelproject.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRoomNotFoundException(RoomNotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(404)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("ROOM_NOT_FOUND")
                .build();

        return ResponseEntity.status(404).body(errorMessage);
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleRoomAlreadyExistsException(RoomAlreadyExistsException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(409)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("ROOM_ALREADY_EXISTS")
                .build();

        return ResponseEntity.status(409).body(errorMessage);
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

    @ExceptionHandler(RoomAlreadyBookedException.class)
    public ResponseEntity<ErrorMessage> handleRoomAlreadyBookedException(RoomAlreadyBookedException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(409)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("ROOM_ALREADY_BOOKED")
                .build();

        return ResponseEntity.status(409).body(errorMessage);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorMessage> handleRoomNotAvailableException(RoomNotAvailableException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(409)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("ROOM_NOT_AVAILABLE")
                .build();

        return ResponseEntity.status(409).body(errorMessage);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleBookingNotFoundException(BookingNotFoundException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(404)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("BOOKING_NOT_FOUND")
                .build();

        return ResponseEntity.status(404).body(errorMessage);
    }

    @ExceptionHandler(IllegalDateException.class)
    public ResponseEntity<ErrorMessage> handleIllegalDateException(IllegalDateException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(400)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("ILLEGAL_DATE")
                .build();

        return ResponseEntity.status(400).body(errorMessage);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(UsernameAlreadyExistsException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(409)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("USERNAME_ALREADY_EXISTS")
                .build();

        return ResponseEntity.status(409).body(errorMessage);
    }

    @ExceptionHandler(UserIDAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUserIDAlreadyExistsException(UserIDAlreadyExistsException ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(409)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("USER_ID_ALREADY_EXISTS")
                .build();

        return ResponseEntity.status(409).body(errorMessage);
    }

    @ExceptionHandler(EmailAlreadyExistsExcepction.class)
    public ResponseEntity<ErrorMessage> handleEmailAlreadyExistsException(EmailAlreadyExistsExcepction ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(409)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("EMAIL_ALREADY_EXISTS")
                .build();

        return ResponseEntity.status(409).body(errorMessage);
    }

    @ExceptionHandler(InvalidPasswordExcepction.class)
    public ResponseEntity<ErrorMessage> handleInvalidPasswordException(InvalidPasswordExcepction ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(401)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("INVALID_PASSWORD")
                .build();

        return ResponseEntity.status(401).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundExcepction.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundExcepction ex, HttpServletRequest request) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .cause(ex.getCause() != null ? ex.getCause().toString() : null)
                .statusCode(404)
                .timestamp(System.currentTimeMillis())
                .path(request.getRequestURI())
                .errorCode("USER_NOT_FOUND")
                .build();

        return ResponseEntity.status(404).body(errorMessage);
    }
}
