package com.emte.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreOrder {
    private Integer cardId;
    private Integer userId;
}
