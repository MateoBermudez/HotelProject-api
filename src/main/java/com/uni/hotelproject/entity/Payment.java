package com.uni.hotelproject.entity;

import com.uni.hotelproject.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payment")
@Builder
public class Payment {
    @Id
    @Column(name = "payment_id", unique = true, nullable = false)
    private String paymentID;


    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private double amount;

    private double amountPaid;

    @Temporal(TemporalType.DATE)
    private LocalDate paymentDate;

    PaymentType paymentType;

    private String cardNumber;

    private String userid;

}
