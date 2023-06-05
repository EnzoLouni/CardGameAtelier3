package com.emte.dto;

import com.emte.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    @JsonView(Views.CardView.class)
    private Integer id;
    @JsonView({Views.UserView.class, Views.CardView.class, Views.StoreView.class})
    private String name;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private String description;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private String family;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private String affinity;
    @JsonView({Views.UserView.class, Views.CardView.class, Views.StoreView.class})
    private String imgUrl;
    @JsonView({Views.UserView.class, Views.CardView.class, Views.StoreView.class})
    private String smallImgUrl;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private Double energy;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private Double hp;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private Double defense;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private Double attack;
    @JsonView({Views.UserView.class, Views.CardView.class})
    private Double price;
    private Integer userId;
}
