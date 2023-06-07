package com.emte.dto;

import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonView({Views.UserView.class, Views.StoreView.class, Views.RoomView.class})
    private Integer id;
    @JsonView({Views.UserView.class, Views.StoreView.class, Views.RoomView.class})
    private String login;
    private String password;
    @JsonView({Views.UserView.class, Views.StoreView.class, Views.RoomView.class})
    private String firstname;
    @JsonView({Views.UserView.class, Views.StoreView.class, Views.RoomView.class})
    private String surname;
    @JsonView({Views.UserView.class, Views.StoreView.class, Views.RoomView.class})
    private String email;
    @JsonView({Views.UserView.class, Views.CardView.class, Views.RoomView.class})
    private List<CardDto> cardDtos;
    @JsonView({Views.UserView.class, Views.StoreView.class, Views.RoomView.class})
    private Double wallet;
}
