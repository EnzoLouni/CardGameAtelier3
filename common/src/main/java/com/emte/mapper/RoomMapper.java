package com.emte.mapper;

import com.emte.client.UserClient;
import com.emte.dto.CardDto;
import com.emte.dto.RoomDto;
import com.emte.model.Card;
import com.emte.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public abstract class RoomMapper {

    @Autowired
    protected UserClient userClient;

    @Mapping(target = "id", source = "room.id")
    @Mapping(target = "name", source = "room.name")
    @Mapping(target = "user1", expression = "java(userClient.getUser(room.getUser1()))")
    @Mapping(target = "user2", expression = "java(userClient.getUser(room.getUser2()))")
    @Mapping(target = "bet", source = "room.bet")
    public abstract RoomDto toRoomDto(Room room);

    @Mapping(target = "id", source = "roomDto.id")
    @Mapping(target = "name", source = "roomDto.name")
    @Mapping(target = "user1", expression = "java(roomDto.getUser1().getId())")
    @Mapping(target = "user2", expression = "java(roomDto.getUser2().getId())")
    @Mapping(target = "bet", source = "roomDto.bet")
    public abstract Room toRoom(RoomDto roomDto);
}
