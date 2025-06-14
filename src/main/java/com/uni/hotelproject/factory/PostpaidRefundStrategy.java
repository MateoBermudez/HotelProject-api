package com.uni.hotelproject.factory;

import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class PostpaidRefundStrategy implements RefundStrategy {


    @Override
    public Refund calculateRefund(Payment payment, int daysBeforeReservation) {
        return Refund.builder()
                .paymentID(payment)
                .amount(0)
                .refundDate(LocalDate.now())
                .build();
    }
}
