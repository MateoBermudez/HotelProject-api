package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.RefundDTO;
import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;
import com.uni.hotelproject.repository.PaymentRepository;
import com.uni.hotelproject.repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefundMapper {

    public RefundDTO refundToRefundDTO(Refund refund) {
        if (refund == null) {
            return null;
        }

        return RefundDTO.builder()
                .refundID(refund.getRefundID())
                .paymentID(refund.getPaymentID().getPaymentID())
                .refundDate(refund.getRefundDate())
                .amount(refund.getAmount())
                .build();
    }

}