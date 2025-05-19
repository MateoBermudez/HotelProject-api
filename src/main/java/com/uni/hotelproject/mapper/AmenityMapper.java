package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.AmenityDTO;
import com.uni.hotelproject.entity.Amenity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AmenityMapper {
     AmenityMapper INSTANCE = Mappers.getMapper(AmenityMapper.class);

     @Mapping(source = "id", target = "id")
     @Mapping(source = "name", target = "name")
     @Mapping(source = "description", target = "description")
     @Mapping(source = "available", target = "available")
     AmenityDTO amenityToAmenityDTO(Amenity amenity);

     @Mapping(source = "id", target = "id")
     @Mapping(source = "name", target = "name")
     @Mapping(source = "description", target = "description")
     @Mapping(source = "available", target = "available")
     Amenity amenityDTOToAmenity(AmenityDTO amenityDTO);
}
