package com.emte.mapper;

import com.emte.client.CardClient;
import com.emte.dto.UserDto;
import com.emte.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.InjectionStrategy.FIELD;

@Mapper(injectionStrategy = FIELD, componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected CardClient cardClient;

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "password", source = "user.password")
    @Mapping(target = "firstname", source = "user.firstname")
    @Mapping(target = "surname", source = "user.surname")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "cardDtos", expression = "java(cardClient.getCardsByUser(user.getId()))")
    public abstract UserDto toUserDto(User user);

    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "login", source = "userDto.login")
    @Mapping(target = "password", source = "userDto.password")
    @Mapping(target = "firstname", source = "userDto.firstname")
    @Mapping(target = "surname", source = "userDto.surname")
    @Mapping(target = "email", source = "userDto.email")
    public abstract User toUser(UserDto userDto);
}
