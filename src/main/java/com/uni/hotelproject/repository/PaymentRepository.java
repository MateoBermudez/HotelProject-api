package com.uni.hotelproject.repository;

import com.uni.hotelproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository <Payment, String> {

    boolean existsPaymentByPaymentID(String paymentID);

    Payment findByPaymentID(String paymentID);
}
