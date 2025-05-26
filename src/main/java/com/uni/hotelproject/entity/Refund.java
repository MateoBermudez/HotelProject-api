package com.uni.hotelproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String paymentID;

    private double amount;

    private String reason;

    @Temporal(TemporalType.DATE)
    private Date refundDate;


}
