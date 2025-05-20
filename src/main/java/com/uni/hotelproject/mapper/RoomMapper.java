package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;
import com.uni.hotelproject.factory.RoomFactoryProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "roomNumber", source = "roomNumber")
    @Mapping(target = "pricePerNight", source = "pricePerNight")
    @Mapping(target = "capacity", source = "capacity")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amenities", source = "amenities")
    @Mapping(target = "roomType", source = "roomType")
    RoomDTO roomToRoomDTO(Room room);

    // We need a default method to handle the creation of the Room object. Room is implementing a factory pattern,
    // and we need to use the factory to create the object. An interface alone cannot create the object through the factory.
    @Mapping(target = "id", source = "id")
    @Mapping(target = "roomNumber", source = "roomNumber")
    @Mapping(target = "pricePerNight", source = "pricePerNight")
    @Mapping(target = "capacity", source = "capacity")
    @Mapping(target = "available", source = "available")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "amenities", source = "amenities")
    @Mapping(target = "roomType", source = "roomType")
    default Room roomDTOToRoom(RoomDTO roomDTO) {
        Room room = RoomFactoryProvider.getFactory(roomDTO.getRoomType()).createRoom(roomDTO);
        room.setId(roomDTO.getId());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setCapacity(roomDTO.getCapacity());
        room.setAvailable(roomDTO.getAvailable());
        room.setDescription(roomDTO.getDescription());
        room.setAmenities(roomDTO.getAmenities().stream().map(AmenityMapper.INSTANCE::amenityDTOToAmenity).toList());
        return room;
    }

}
