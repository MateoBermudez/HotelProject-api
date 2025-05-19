package com.uni.hotelproject.factory;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.DeluxeRoom;
import com.uni.hotelproject.entity.Room;

public class DeluxeRoomFactory implements RoomFactory {

    @Override
    public Room createRoom(RoomDTO roomDTO) {
        return new DeluxeRoom(
                roomDTO.getRoomNumber(),
                roomDTO.getPricePerNight(),
                roomDTO.getCapacity(),
                roomDTO.getAvailable(),
                roomDTO.getDescription()
        );
    }
}
