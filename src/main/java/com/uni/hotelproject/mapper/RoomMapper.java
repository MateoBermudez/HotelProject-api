package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;
import com.uni.hotelproject.factory.RoomFactoryProvider;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public static RoomDTO roomToRoomDTO(Room room) {
        if (room == null) {
            return null;
        }
        return RoomDTO.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .pricePerNight(room.getPricePerNight())
                .capacity(room.getCapacity())
                .available(room.getAvailable())
                .description(room.getDescription())
                .amenities(room.getAmenities() == null ? null : room.getAmenities().stream()
                        .map(AmenityMapper::amenityToAmenityDTO)
                        .toList())
                .roomType(room.getRoomType())
                .build();
    }

    public static Room roomDTOToRoom(RoomDTO roomDTO) {
        if (roomDTO == null) {
            return null;
        }
        Room room = RoomFactoryProvider.getFactory(roomDTO.getRoomType()).createRoom(roomDTO);
        room.setId(roomDTO.getId());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setPricePerNight(roomDTO.getPricePerNight());
        room.setCapacity(roomDTO.getCapacity());
        room.setAvailable(roomDTO.getAvailable());
        room.setDescription(roomDTO.getDescription());
        room.setAmenities(roomDTO.getAmenities() == null ? null : roomDTO.getAmenities().stream()
                .map(AmenityMapper::amenityDTOToAmenity)
                .toList());
        return room;
    }
}