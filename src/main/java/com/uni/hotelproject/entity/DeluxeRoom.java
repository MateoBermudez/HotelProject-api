package com.uni.hotelproject.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("DELUXE")
public class DeluxeRoom extends Room{

    public DeluxeRoom(String roomNumber, Double pricePerNight, Integer capacity, Boolean available, String description, List<Amenity> amenities) {
        super(roomNumber, pricePerNight, capacity, available, description, amenities);
    }

    public DeluxeRoom() {
        super();
    }

    @Override
    public Double calculateFinalPrice(int numberOfNights) {
        return getPricePerNight() * numberOfNights * 1.05; // Assuming deluxe rooms have a 5% surcharge for good services
    }
}
