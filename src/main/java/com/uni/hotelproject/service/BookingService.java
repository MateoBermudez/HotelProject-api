package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.BookingDTO;
import com.uni.hotelproject.dto.RoomAvailabilityDTO;
import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Booking;
import com.uni.hotelproject.entity.Room;
import com.uni.hotelproject.exception.*;
import com.uni.hotelproject.mapper.BookingMapper;
import com.uni.hotelproject.mapper.RoomMapper;
import com.uni.hotelproject.repository.BookingRepository;
import com.uni.hotelproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(BookingMapper::bookingToBookingDTO)
                .toList();
    }

    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.bookingDTOToBooking(bookingDTO);

        // Check dates
        if (booking.getStartDate().isAfter(booking.getEndDate()) || booking.getStartDate().isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalDateException("Start date must be before end date and at least one day after today");
        }

        // Check if the room is already booked
        Room room = booking.getRoom();
        if (!room.getAvailable()) {
            throw new RoomNotAvailableException("Room " + room.getRoomNumber() + " is not available for booking");
        }

        boolean overlappingBookingExists = bookingRepository.existsByRoomAndDatesOverlap(
                room.getId(), booking.getStartDate(), booking.getEndDate()
        );
        if (overlappingBookingExists) {
            throw new RoomAlreadyBookedException("Room " + room.getRoomNumber() + " is already booked for the selected dates");
        }

        booking.calculateTotalPrice();

        booking = bookingRepository.save(booking);
        return BookingMapper.bookingToBookingDTO(booking);
    }

    @Transactional
    public void confirmBooking(Long bookingId) {
        // Confirm the booking -> When the user pays, the booking is confirmed
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new RuntimeException("Booking with ID " + bookingId + " not found")
        );
        booking.setConfirmed(true);
        bookingRepository.save(booking);
    }

    @Scheduled(cron = "0 0 0 * * ?") // Execute daily at midnight
    @Transactional
    public void deleteOldUnconfirmedBookings() {
        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
        bookingRepository.deleteByConfirmedFalseAndCreatedAtBefore(sevenDaysAgo);
    }

    @Transactional
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new RuntimeException("Booking with ID " + bookingId + " not found")
        );
        bookingRepository.delete(booking);
    }

    @Transactional(readOnly = true)
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new BookingNotFoundException("Booking with ID " + bookingId + " not found")
        );
        return BookingMapper.bookingToBookingDTO(booking);
    }

    @Transactional(readOnly = true)
    public RoomAvailabilityDTO getRoomAvailability(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new RoomNotFoundException("Room with number " + roomNumber + " not found")
        );

        List<Booking> bookings = bookingRepository.findByRoom(room);
        List<LocalDate> unavailableDates = bookings.stream()
                .flatMap(booking -> booking.getStartDate().datesUntil(booking.getEndDate().plusDays(1)))
                .toList();

        return new RoomAvailabilityDTO(roomNumber, unavailableDates);
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getAvailableRooms(LocalDate startDate, LocalDate endDate) {
        List<Room> availableRooms = roomRepository.findAll().stream()
                .filter(room -> bookingRepository.findByRoomAndDatesOverlap(room.getId(), startDate, endDate).isEmpty())
                .toList();

        return availableRooms.stream()
                .map(RoomMapper::roomToRoomDTO)
                .toList();
    }
}
