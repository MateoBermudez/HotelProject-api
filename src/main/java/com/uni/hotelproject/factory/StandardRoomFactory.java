package com.uni.hotelproject.factory;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;
import com.uni.hotelproject.entity.StandardRoom;
import com.uni.hotelproject.mapper.AmenityMapper;

public class StandardRoomFactory implements RoomFactory {
    @Override
    public Room createRoom(RoomDTO roomDTO) {
        return new StandardRoom(
                roomDTO.getRoomNumber(),
                roomDTO.getPricePerNight(),
                roomDTO.getCapacity(),
                roomDTO.getAvailable(),
                roomDTO.getDescription(),
                roomDTO.getAmenities().stream()
                        .map(AmenityMapper::amenityDTOToAmenity)
                        .toList()
        );
    }
}
