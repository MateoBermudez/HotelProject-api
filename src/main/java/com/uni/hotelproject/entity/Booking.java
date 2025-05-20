package com.uni.hotelproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_number", nullable = false)
    private Room room;

    // Later we can add a User entity and link it here, for now we will just use a String
    @NotNull
    @Column(name = "customer_name")
    private String customerName;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "notes")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "total_price")
    private Double totalPrice;

    public void calculateTotalPrice() {
        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        if (nights <= 0) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.totalPrice = nights * room.getPricePerNight();
    }
}
