package com.emte.usermanager.controller;

import com.emte.dto.AuthDto;
import com.emte.dto.UserDto;
import com.emte.usermanager.service.UserClientService;
import com.emte.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private")
public class UserPrivateController {
    private final UserClientService userClientService;
    private final UserService userService;

    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable(name = "id") Integer id) {
        return userClientService.getUser(id);
    }

    @PostMapping("/auth")
    public boolean authentication(@RequestBody @Valid AuthDto request)
    {
        return userService.authenticate(request);
    }
}
