package com.uni.hotelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundDTO {
    private String refundID;
    private String paymentID;
    private double amount;
    private String reason;
    private String refundDate;
    
}
