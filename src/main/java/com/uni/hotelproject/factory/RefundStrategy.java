package com.uni.hotelproject.factory;

import com.uni.hotelproject.entity.Payment;
import com.uni.hotelproject.entity.Refund;

public interface RefundStrategy {

    Refund calculateRefund(Payment payment, int daysBeforeReservation);

}
