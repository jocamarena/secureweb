package com.example.secureweb.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hello {
    private String message;
    private LocalDateTime localDateTime;
}
