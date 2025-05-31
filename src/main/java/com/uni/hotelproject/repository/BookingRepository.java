package com.uni.hotelproject.repository;

import com.uni.hotelproject.entity.Booking;
import com.uni.hotelproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.room.id = :roomId AND " +
            "(b.startDate < :endDate AND b.endDate > :startDate)")
    boolean existsByRoomAndDatesOverlap(@Param("roomId") Long roomId,
                                        @Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
            "(b.startDate <= :endDate AND b.endDate >= :startDate)")
    List<Booking> findByRoomAndDatesOverlap(@Param("roomId") Long roomId,
                                            @Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

    @Modifying
    @Query("DELETE FROM Booking b WHERE b.confirmed = false AND b.createdAt < :date")
    void deleteByConfirmedFalseAndCreatedAtBefore(LocalDate date);

    @Query("SELECT b FROM Booking b WHERE b.room = :room")
    List<Booking> findByRoom(Room room);
}
