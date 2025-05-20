package com.uni.hotelproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payment")

public class Payment {
    @Id
    @Column(name = "payment_id", unique = true, nullable = false)
    private String paymentID;

    private String reservationID;
    private double amount;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;
}
