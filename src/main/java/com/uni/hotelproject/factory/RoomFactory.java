package com.uni.hotelproject.factory;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;

public interface RoomFactory {
    Room createRoom(RoomDTO roomDTO);
}
