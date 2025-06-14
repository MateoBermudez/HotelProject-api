package com.uni.hotelproject.repository;

import com.uni.hotelproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, String> {

    boolean existsPaymentByPaymentID(String paymentID);

    @Query ("SELECT p FROM Payment p WHERE p.paymentID = ?1")
    Payment findPaymentByPaymentID(String paymentID);

    @Query("SELECT p FROM Payment p WHERE p.booking.id = ?1")
    List<Payment> findAllByuserId(String bookingId);


    @Query("SELECT p FROM Payment p WHERE p.booking.id = ?1")
    Payment findPaymentByBookingId(long bookingId);


}
