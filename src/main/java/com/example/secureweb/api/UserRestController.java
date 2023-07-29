package com.example.secureweb.api;

import com.example.secureweb.dto.RegisterSystemUserDto;
import com.example.secureweb.dto.SystemUserDto;
import com.example.secureweb.model.users.SystemUser;
import com.example.secureweb.services.SystemUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private SystemUserService systemUserService;
    private BCryptPasswordEncoder passwordEncoder;
    public UserRestController(SystemUserService systemUserService, BCryptPasswordEncoder passwordEncoder) {
        this.systemUserService = systemUserService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public ResponseEntity<SystemUserDto> findUserByUsername(String username){
            SystemUser systemUser = systemUserService.findSystemUserByUsername(username).orElseThrow();
            return ResponseEntity.ok(SystemUserDto.builder()
                    .username(systemUser.getUsername())
                    .authorities(systemUser.getAuthorities())
                    .accountNonExpired(systemUser.isAccountNonExpired())
                    .accountNonLocked(systemUser.isAccountNonLocked())
                    .credentialsNonExpired(systemUser.isCredentialsNonExpired())
                    .enabled(systemUser.isEnabled())
                    .build());
    }
    @PostMapping
    public ResponseEntity<String> createUser(RegisterSystemUserDto userRequestDTO){
        systemUserService.findSystemUserByUsername(userRequestDTO.getUsername()).ifPresent(systemUser -> {
            throw new RuntimeException(String.format("User %s already exists", userRequestDTO.getUsername()));
        });
        SystemUser.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .username(userRequestDTO.getUsername())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()));
        return ResponseEntity.ok(String.format("User %s registered", userRequestDTO.getUsername()));
    }
}
