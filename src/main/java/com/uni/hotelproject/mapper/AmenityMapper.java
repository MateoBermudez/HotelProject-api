package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.AmenityDTO;
import com.uni.hotelproject.entity.Amenity;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

     public static AmenityDTO amenityToAmenityDTO(Amenity amenity) {
          if (amenity == null) {
               return null;
          }
          return AmenityDTO.builder()
                  .id(amenity.getId())
                  .name(amenity.getName())
                  .description(amenity.getDescription())
                  .available(amenity.getAvailable())
                  .build();
     }

     public static Amenity amenityDTOToAmenity(AmenityDTO amenityDTO) {
          if (amenityDTO == null) {
               return null;
          }
          return Amenity.builder()
                  .id(amenityDTO.getId())
                  .name(amenityDTO.getName())
                  .description(amenityDTO.getDescription())
                  .available(amenityDTO.getAvailable())
                  .build();
     }
}