package com.uni.hotelproject.dto;

import com.uni.hotelproject.enums.RoomType;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {

    private Long id;

    private String roomNumber;

    private Double pricePerNight;

    private Integer capacity;

    private Boolean available;

    private String description;

    private RoomType roomType;

    private List<AmenityDTO> amenities;

}
