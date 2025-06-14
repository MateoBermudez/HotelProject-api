package com.uni.hotelproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Long id;

    private RoomDTO room;

    private String customerName;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean confirmed;

    private String notes;

    private LocalDate createdAt;

    private Double totalPrice;
}
