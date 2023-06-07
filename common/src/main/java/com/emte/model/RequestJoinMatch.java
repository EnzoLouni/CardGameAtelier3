package com.emte.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestJoinMatch {
    private Integer roomId;
    private Integer userId;
}
