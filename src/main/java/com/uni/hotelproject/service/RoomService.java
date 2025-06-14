package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;
import com.uni.hotelproject.exception.RoomAlreadyExistsException;
import com.uni.hotelproject.exception.RoomNotFoundException;
import com.uni.hotelproject.factory.RoomFactory;
import com.uni.hotelproject.factory.RoomFactoryProvider;
import com.uni.hotelproject.mapper.RoomMapper;
import com.uni.hotelproject.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;


    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::roomToRoomDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public RoomDTO getRoomByRoomNumber(String roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new RoomNotFoundException("Room with number " + roomNumber + " not found")
        );
        return RoomMapper.roomToRoomDTO(room);
    }

    @Transactional
    public RoomDTO addRoom(RoomDTO roomDTO) {
        if (roomRepository.findByRoomNumber(roomDTO.getRoomNumber()).isPresent()) {
            throw new RoomAlreadyExistsException("Room with number " + roomDTO.getRoomNumber() + " already exists");
        }
        RoomFactory factory = RoomFactoryProvider.getFactory(roomDTO.getRoomType());
        Room room = factory.createRoom(roomDTO);
        room = roomRepository.save(room);
        return RoomMapper.roomToRoomDTO(room);
    }

    @Transactional
    public RoomDTO updateRoom(RoomDTO roomDTO) {
        roomRepository.findByRoomNumber(roomDTO.getRoomNumber()).orElseThrow(
                () -> new RoomNotFoundException("Room with number " + roomDTO.getRoomNumber() + " not found")
        );
        Room roomToUpdate = RoomMapper.roomDTOToRoom(roomDTO);
        roomRepository.save(roomToUpdate);
        return RoomMapper.roomToRoomDTO(roomToUpdate);
    }

    @Transactional
    public void deleteRoom(String roomNumber) {
        roomRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new RoomNotFoundException("Room with number " + roomNumber + " not found")
        );
        roomRepository.deleteRoomByRoomNumber(roomNumber);
    }
}
