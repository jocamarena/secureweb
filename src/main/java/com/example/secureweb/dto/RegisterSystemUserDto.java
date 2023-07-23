package com.example.secureweb.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSystemUserDto {
    private String username;
    private String password;
}
