package com.uni.hotelproject.factory;

import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class PrepaidRefundStrategy implements RefundStrategy {


    @Override
    public Refund calculateRefund(Payment payment, int daysBeforeReservation) {
        if (daysBeforeReservation >= 7) {
            return Refund.builder()
                    .paymentID(payment)
                    .amount(payment.getAmount())
                    .refundDate(LocalDate.now())
                    .build();
        } else if (daysBeforeReservation >= 1) {
            return Refund.builder()
                    .paymentID(payment)
                    .amount((int) (payment.getAmount() * 0.5)) // 50% refund
                    .refundDate(LocalDate.now())
                    .build();
        } else {
            return Refund.builder()
                    .paymentID(payment)
                    .amount(0) // No refund
                    .refundDate(LocalDate.now())
                    .build();
        }
    }


}
