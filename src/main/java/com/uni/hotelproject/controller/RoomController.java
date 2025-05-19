package com.uni.hotelproject.controller;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @PostMapping("/add")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.addRoom(roomDTO));
    }

    @PutMapping("/update/{roomNumber}")
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.updateRoom(roomDTO));
    }

    // Can be done like this or with RequestBody, requesting the whole object
    @DeleteMapping("/delete/{roomNumber}")
    public void deleteRoom(@PathVariable String roomNumber) {
        roomService.deleteRoom(roomNumber);
    }
}
