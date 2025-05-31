package com.uni.hotelproject.entity;

import com.uni.hotelproject.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "room_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @NotNull
    @Column(name = "room_number", unique = true)
    private String roomNumber;

    @NotNull
    @Column(name = "price_per_night")
    private Double pricePerNight;

    @NotNull
    @Column(name = "capacity")
    private Integer capacity;

    @NotNull
    @Column(name = "available")
    private Boolean available;

    @NotNull
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "room_id")
    private List<Amenity> amenities;

    @Transient
    private RoomType roomType = getRoomType();

    public RoomType getRoomType() {
        DiscriminatorValue discriminatorValue = this.getClass().getAnnotation(DiscriminatorValue.class);
        return RoomType.valueOf(discriminatorValue.value());
    }

    public abstract Double calculateFinalPrice(int numberOfNights);

    public Room(String roomNumber, Double pricePerNight, Integer capacity, Boolean available, String description, List<Amenity> amenities) {
        this.roomNumber = roomNumber;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.available = available;
        this.description = description;
        this.amenities = amenities;
    }
}
