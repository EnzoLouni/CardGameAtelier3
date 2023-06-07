package com.emte.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreationCard {
    private String name;
    private String description;
    private String family;
    private String affinity;
    private String imgUrl;
    private String smallImgUrl;
    private Double energy;
    private Double hp;
    private Double defense;
    private Double attack;
    private Double price;
}
