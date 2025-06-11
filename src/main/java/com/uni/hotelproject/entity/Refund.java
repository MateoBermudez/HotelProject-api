package com.uni.hotelproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Refund")
public class Refund {

    @Id
    private String refundID;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentID;

    private double amount;

    @Temporal(TemporalType.DATE)
    private LocalDate refundDate;


}
