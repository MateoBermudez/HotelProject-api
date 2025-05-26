package com.uni.hotelproject.factory;

import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PrepaidRefundStrategy implements RefundStrategy {

    @Autowired
    private RefundService refundService;

    @Override
    public Refund calculateRefund(Payment payment, int daysBeforeReservation) {
        if (daysBeforeReservation >= 7) {
            return Refund.builder()
                    .refundID(refundService.generateRandomRefundId())
                    .paymentID(payment.getPaymentID())
                    .amount(payment.getAmount())
                    .reason("Full refund because of cancellation")
                    .refundDate(new Date())
                    .build();
        } else if (daysBeforeReservation >= 1) {
            return Refund.builder()
                    .refundID(refundService.generateRandomRefundId())
                    .paymentID(payment.getPaymentID())
                    .amount((int) (payment.getAmount() * 0.5)) // 50% refund
                    .reason("Partial refund because of cancellation")
                    .refundDate(new Date())
                    .build();
        } else {
            return Refund.builder()
                    .refundID(refundService.generateRandomRefundId())
                    .paymentID(payment.getPaymentID())
                    .amount(0) // No refund
                    .reason("No refund because of cancellation within 24 hours")
                    .refundDate(new Date())
                    .build();
        }
    }


}
