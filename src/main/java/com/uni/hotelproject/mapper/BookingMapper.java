package com.uni.hotelproject.mapper;

import com.uni.hotelproject.dto.BookingDTO;
import com.uni.hotelproject.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public static BookingDTO bookingToBookingDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        return BookingDTO.builder()
                .id(booking.getId())
                .room(RoomMapper.roomToRoomDTO(booking.getRoom()))
                .customerName(booking.getCustomerName())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .confirmed(booking.isConfirmed())
                .notes(booking.getNotes())
                .createdAt(booking.getCreatedAt())
                .totalPrice(booking.getTotalPrice())
                .build();
    }

    public static Booking bookingDTOToBooking(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }
        return Booking.builder()
                .id(bookingDTO.getId())
                .room(RoomMapper.roomDTOToRoom(bookingDTO.getRoom()))
                .customerName(bookingDTO.getCustomerName())
                .startDate(bookingDTO.getStartDate())
                .endDate(bookingDTO.getEndDate())
                .confirmed(bookingDTO.isConfirmed())
                .notes(bookingDTO.getNotes())
                .createdAt(bookingDTO.getCreatedAt())
                .totalPrice(bookingDTO.getTotalPrice())
                .build();
    }
}

