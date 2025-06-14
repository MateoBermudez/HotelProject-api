package com.uni.hotelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private String paymentID;

    private Long bookingID;

    private double amount;

    private double amountPaid;

    private LocalDate paymentDate;

    private String paymentType;

    private String cardNumber;

    private String userid;
}
