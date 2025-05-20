package com.uni.hotelproject.repository;

import com.uni.hotelproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.roomNumber = ?1")
    Optional<Room> findByRoomNumber(String roomNumber);

    void deleteRoomByRoomNumber(String roomNumber);
}
