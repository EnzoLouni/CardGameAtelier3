package com.emte.client;

import com.emte.dto.AuthDto;
import com.emte.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "userClient", url = "${user.ribbon.listOfServers}/userapi")
public interface UserClient {

    @GetMapping("/private/users/{id}")
    UserDto getUser(@PathVariable(name = "id") Integer id);

    @PutMapping("/users/{id}")
    UserDto updateUser(@PathVariable(name = "id") Integer id, @RequestBody @Valid UserDto newUserDto);

    @PostMapping("/private/auth")
    boolean authentication(@RequestBody @Valid AuthDto request);
}
