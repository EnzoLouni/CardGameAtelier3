package com.emte.model;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreationUser {
    private String login;
    private String password;
    private String firstname;
    private String surname;
    private String email;
}
