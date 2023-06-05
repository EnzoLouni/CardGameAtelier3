package com.emte.client;

import com.emte.dto.AuthDto;
import com.emte.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "userClient", url = "http://localhost:8081/userapi")
public interface UserClient {

    @GetMapping("/private/users/{id}")
    UserDto getUser(@PathVariable(name = "id") Integer id);

    @PostMapping("/private/auth")
    boolean authentication(@RequestBody @Valid AuthDto request);
}
