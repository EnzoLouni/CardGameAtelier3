package com.emte.dto;

import com.emte.model.StoreAction;
import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreTransactionDto {
    @JsonView(Views.StoreView.class)
    private Integer id;
    @JsonView({Views.StoreView.class, Views.UserView.class})
    private UserDto userDto;
    @JsonView({Views.StoreView.class, Views.CardView.class})
    private CardDto cardDto;
    @JsonView(Views.StoreView.class)
    private StoreAction action;
    @JsonView(Views.StoreView.class)
    private Date timestamp;
}
