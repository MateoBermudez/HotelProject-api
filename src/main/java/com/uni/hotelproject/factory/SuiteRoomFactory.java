package com.uni.hotelproject.factory;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;
import com.uni.hotelproject.entity.SuiteRoom;
import com.uni.hotelproject.mapper.AmenityMapper;

public class SuiteRoomFactory implements RoomFactory {

    @Override
    public Room createRoom(RoomDTO roomDTO) {
        return new SuiteRoom(
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
