package com.emte.dto;

import com.emte.model.User;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @JsonView(Views.RoomView.class)
    private Integer id;
    @JsonView(Views.RoomView.class)
    private String name;
    @JsonView({Views.RoomView.class, Views.UserView.class})
    private UserDto user1;
    @JsonView({Views.RoomView.class, Views.UserView.class})
    private UserDto user2;
    @JsonView(Views.RoomView.class)
    private Double bet;
}
