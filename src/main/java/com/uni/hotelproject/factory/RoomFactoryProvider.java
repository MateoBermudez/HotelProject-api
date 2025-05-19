package com.uni.hotelproject.factory;

import com.uni.hotelproject.enums.RoomType;

public class RoomFactoryProvider {

    public static RoomFactory getFactory(RoomType type) {
        return switch (type) {
            case STANDARD -> new StandardRoomFactory();
            case DELUXE -> new DeluxeRoomFactory();
            case SUITE -> new SuiteRoomFactory();
        };
    }
}