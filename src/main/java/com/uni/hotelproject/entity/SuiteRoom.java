package com.uni.hotelproject.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SUITE")
public class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber, Double pricePerNight, Integer capacity, Boolean available, String description) {
        super(roomNumber, pricePerNight, capacity, available, description);
    }

    public SuiteRoom() {
        super();
    }


    @Override
    public Double calculateFinalPrice(int numberOfNights) {
        return getPricePerNight() * numberOfNights * 1.1; // Assuming suite rooms have a 10% surcharge for premium services
    }
}
