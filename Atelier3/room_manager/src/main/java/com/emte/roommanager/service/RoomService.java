package com.emte.roommanager.service;

import com.emte.client.CardClient;
import com.emte.client.UserClient;
import com.emte.dto.RoomDto;
import com.emte.dto.UserDto;
import com.emte.mapper.RoomMapper;
import com.emte.model.RequestCreationRoom;
import com.emte.model.RequestJoinMatch;
import com.emte.model.Room;
import com.emte.roommanager.dao.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final UserClient userClient;
    private final CardClient cardClient;

    public RoomDto getRoom(Integer roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return roomMapper.toRoomDto(room.get());
    }

    public List<RoomDto> getRooms() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(),false).map(roomMapper::toRoomDto).collect(toList());
    }

    public RoomDto createRoom(RequestCreationRoom request) {
        UserDto creator = userClient.getUser(request.getCreatorId());
        creator.setWallet(creator.getWallet() - request.getBet());
        userClient.updateUser(creator.getId(), creator);
        RoomDto newRoomDto = RoomDto.builder()
                .name(request.getName())
                .user1(creator)
                .bet(request.getBet())
                .build();
        roomRepository.save(roomMapper.toRoom(newRoomDto));
        return newRoomDto;
    }

    public RoomDto joinRoom(RequestJoinMatch request) {
        RoomDto roomDto = getRoom(request.getRoomId());
        UserDto user2 = userClient.getUser(request.getUserId());
        user2.setWallet(user2.getWallet() - roomDto.getBet());
        userClient.updateUser(user2.getId(), user2);
        roomDto.setUser2(user2);
        roomRepository.save(roomMapper.toRoom(roomDto));
        return roomDto;
    }
}
