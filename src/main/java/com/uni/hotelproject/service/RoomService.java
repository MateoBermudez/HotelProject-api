package com.uni.hotelproject.service;

import com.uni.hotelproject.dto.RoomDTO;
import com.uni.hotelproject.entity.Room;
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
                .map(RoomMapper.INSTANCE::roomToRoomDTO)
                .toList();
    }

    @Transactional
    public RoomDTO addRoom(RoomDTO roomDTO) {
        RoomFactory factory = RoomFactoryProvider.getFactory(roomDTO.getRoomType());
        Room room = factory.createRoom(roomDTO);
        room = roomRepository.save(room);
        return RoomMapper.INSTANCE.roomToRoomDTO(room);
    }

    @Transactional
    public RoomDTO updateRoom(RoomDTO roomDTO) {
        roomRepository.findByRoomNumber(roomDTO.getRoomNumber()).orElseThrow(
                () -> new RoomNotFoundException("Room with number " + roomDTO.getRoomNumber() + " not found")
        );
        Room roomToUpdate = RoomMapper.INSTANCE.roomDTOToRoom(roomDTO);
        roomRepository.save(roomToUpdate);
        return RoomMapper.INSTANCE.roomToRoomDTO(roomToUpdate);
    }

    @Transactional
    public void deleteRoom(String roomNumber) {
        roomRepository.deleteRoomByRoomNumber(roomNumber);
    }
}
