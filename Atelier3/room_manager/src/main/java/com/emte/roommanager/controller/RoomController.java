package com.emte.roommanager.controller;

import com.emte.dto.RoomDto;
import com.emte.model.RequestCreationRoom;
import com.emte.model.RequestJoinMatch;
import com.emte.roommanager.service.RoomService;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class RoomController {
    private final RoomService roomService;

    @JsonView(Views.RoomView.class)
    @GetMapping("/rooms")
    public List<RoomDto> getRooms()
    {
        return roomService.getRooms();
    }

    @JsonView(Views.RoomView.class)
    @PostMapping("/room")
    public RoomDto createRoom(@RequestBody @Valid RequestCreationRoom room)
    {
        return roomService.createRoom(room);
    }

    @JsonView(Views.RoomView.class)
    @PostMapping("/room/join/")
    public RoomDto joinRoom(@RequestBody @Valid RequestJoinMatch request)
    {
        return roomService.joinRoom(request);
    }
}
