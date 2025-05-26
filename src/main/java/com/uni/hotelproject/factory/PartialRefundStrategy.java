package com.uni.hotelproject.factory;

import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PartialRefundStrategy implements RefundStrategy{

    @Autowired
    private RefundService refundService;

    @Override
    public Refund calculateRefund(Payment payment, int daysBeforeReservation) {
        if (daysBeforeReservation >= 7) {
            return (Refund.builder()
                    .refundID(refundService.generateRandomRefundId())
                    .paymentID(payment.getPaymentID())
                    .amount(payment.getAmount() * 0.2)
                    .reason("20% refund because of cancellation")
                    .refundDate(new Date())
                    .build());
        } else {
            return (Refund.builder()
                    .refundID(refundService.generateRandomRefundId())
                    .paymentID(payment.getPaymentID())
                    .amount(0)
                    .reason("No refund because of cancellation")
                    .refundDate(new Date())
                    .build());
        }

    }
}
