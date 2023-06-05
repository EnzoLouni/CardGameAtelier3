package com.emte.usermanager.service;

import com.emte.client.CardClient;
import com.emte.dto.AuthDto;
import com.emte.dto.CardDto;
import com.emte.dto.UserDto;
import com.emte.mapper.UserMapper;
import com.emte.model.User;
import com.emte.usermanager.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Validated
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CardClient cardClient;

    public UserDto getUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return userMapper.toUserDto(user.get());
    }

    public UserDto updateUser(Integer id, UserDto newUserDto) {
        try {
            userRepository.findById(id);
            newUserDto.setId(id);
            newUserDto.setPassword(DigestUtils.sha256Hex(newUserDto.getPassword()));
            userRepository.save(userMapper.toUser(newUserDto));
            return getUser(id);
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    public boolean deleteUser(Integer userId) {
        try {
            cardClient.deleteCardsByUser(userId);
            userRepository.deleteById(userId);
            return true;
        } catch(Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    public UserDto createUser(UserDto user) {
        boolean isAnUsedLogin = userRepository.hasAnExistingLogin(user.getLogin());
        if(!isAnUsedLogin){
            user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
            List<CardDto> cardsToSell = cardClient.getCardsToSell();
            Collections.shuffle(cardsToSell);
            List<CardDto> cardsToAssign;
            UserDto userRegistered = userMapper.toUserDto(userRepository.save(userMapper.toUser(user)));
            if(cardsToSell.size() < 5) {
                cardsToAssign = cardsToSell.subList(0, cardsToSell.size());
                List<CardDto> generatedCards = cardClient.getGeneratedCards();
                generatedCards = generatedCards.stream().filter(cardDto -> {
                    for(CardDto card: cardsToSell) {
                        if(card.getName().equals(cardDto.getName()))
                            return false;
                    }
                    return true;
                }).collect(toList());
                Collections.shuffle(generatedCards);
                generatedCards.subList(0, 5 - cardsToSell.size()).forEach(cardDto -> {
                    cardDto.setUserId(userRegistered.getId());
                    cardClient.createCard(cardDto);
                });
            }
            else {
                cardsToAssign = cardsToSell.subList(0, 5);
            }
            cardsToAssign.forEach(cardDto -> {
                cardDto.setUserId(userRegistered.getId());
                cardClient.updateCard(cardDto.getId(), cardDto);
            });
            return getUser(userRegistered.getId());
        }
        else {
            return null;
        }
    }

    public boolean authenticate(AuthDto request) {
        String passwordGiven = DigestUtils.sha256Hex(request.getPassword());
        String passwordRelated = userRepository.getPasswordByLogin(request.getLogin());
        return passwordGiven.equals(passwordRelated);
    }

    public List<UserDto> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false).map(userMapper::toUserDto).collect(toList());
    }
}
