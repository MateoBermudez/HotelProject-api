package com.uni.hotelproject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMessage {
    private String message;
    private String cause;
    private int statusCode;
    private long timestamp;
    private String path;
    private String errorCode;
}
