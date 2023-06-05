package com.emte.usermanager.service;

import com.emte.dto.UserDto;
import com.emte.mapper.UserMapper;
import com.emte.model.User;
import com.emte.usermanager.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@RequiredArgsConstructor
@Service
public class UserClientService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserDto getUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return null;
        }
        return userMapper.toUserDto(user.get());
    }
}
