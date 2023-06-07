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
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private Integer id;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private String name;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private String description;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private String family;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private String affinity;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private String imgUrl;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private String smallImgUrl;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private Double energy;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private Double hp;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private Double defense;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private Double attack;
    @JsonView({Views.CardView.class, Views.UserView.class, Views.StoreView.class})
    private Double price;
    private Integer userId;
}
