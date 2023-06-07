package com.emte.usermanager.controller;

import com.emte.dto.AuthDto;
import com.emte.dto.UserDto;
import com.emte.model.RequestCreationUser;
import com.emte.usermanager.service.UserService;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class UserPublicController {

    private final UserService userService;

    @JsonView(Views.UserView.class)
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @JsonView(Views.UserView.class)
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable(name = "id") Integer id, @RequestBody @Valid UserDto newUserDto) {
        return userService.updateUser(id, newUserDto);
    }

    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable(name = "id") Integer id) {
        return userService.deleteUser(id);
    }

    @JsonView(Views.UserView.class)
    @PostMapping("/user")
    public UserDto createUser(@RequestBody @Valid RequestCreationUser request)
    {
        return userService.createUser(request);
    }

    @PostMapping("/auth")
    public boolean authentication(@RequestBody @Valid AuthDto request)
    {
        return userService.authenticate(request);
    }

    @JsonView(Views.UserView.class)
    @GetMapping("/users")
    public List<UserDto> getUsers()
    {
        return userService.getUsers();
    }
}

