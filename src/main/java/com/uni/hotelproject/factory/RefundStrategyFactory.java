package com.uni.hotelproject.factory;

import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.enums.PaymentType;
import com.uni.hotelproject.service.RefundService;

import static com.uni.hotelproject.enums.PaymentType.PREPAID;

public class RefundStrategyFactory {
    public static RefundStrategy getStrategy(Payment payment) {
        PaymentType paymentType = payment.getPaymentType();

        switch (paymentType) {
            case PREPAID:
                return new PrepaidRefundStrategy();
            case PARTIAL:
                return new PartialRefundStrategy();
            case POSTPAID:
                return new PostpaidRefundStrategy();
        }
        return null;
    }
}
