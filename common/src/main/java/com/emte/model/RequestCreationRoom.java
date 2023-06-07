package com.emte.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreationRoom {
    private String name;
    private Integer creatorId;
    private Double bet;
}
