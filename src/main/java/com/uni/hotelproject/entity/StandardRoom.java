package com.uni.hotelproject.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STANDARD")
public class StandardRoom extends Room {

    public StandardRoom(String roomNumber, Double pricePerNight, Integer capacity, Boolean available, String description) {
        super(roomNumber, pricePerNight, capacity, available, description);
    }

    public StandardRoom() {
        super();
    }

    @Override
    public Double calculateFinalPrice(int numberOfNights) {
        return getPricePerNight() * numberOfNights; // Standard rooms have no surcharge for basic services
    }
}
