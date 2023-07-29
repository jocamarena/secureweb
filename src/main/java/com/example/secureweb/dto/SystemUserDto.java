package com.example.secureweb.dto;

import com.example.secureweb.model.users.SystemAuthority;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserDto {

    private  String username;

    private Set<SystemAuthority> authorities;

    private  boolean accountNonExpired = true;

    private  boolean accountNonLocked = true;

    private  boolean credentialsNonExpired = true;

    private  boolean enabled = true;
}
