package com.example.secureweb.web;

import com.example.secureweb.dto.RegisterSystemUserDto;
import com.example.secureweb.model.Hello;
import com.example.secureweb.model.users.SystemAuthority;
import com.example.secureweb.model.users.SystemUser;
import com.example.secureweb.services.SystemUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DefaultController {
    private SystemUserService systemUserService;
    private BCryptPasswordEncoder passwordEncoder;
    public DefaultController(SystemUserService systemUserService, BCryptPasswordEncoder passwordEncoder) {
        this.systemUserService = systemUserService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public Hello home() {
        return Hello.builder()
                .message("Hello, World!")
                .localDateTime(java.time.LocalDateTime.now())
                .build();
    }
    @GetMapping("/secure/home")
    public Hello homeSecure() {
        return Hello.builder()
                .message("Hello, Secure World!")
                .localDateTime(java.time.LocalDateTime.now())
                .build();
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterSystemUserDto registerSystemUserDto){
        if (systemUserService.findSystemUserByUsername(registerSystemUserDto.getUsername()).isEmpty()) {
            systemUserService.saveSystemUser(SystemUser.builder()
                    .password(passwordEncoder.encode(registerSystemUserDto.getPassword()))
                    .username(registerSystemUserDto.getUsername())
                    .enabled(true)
                    .credentialsNonExpired(true)
                    .accountNonLocked(true)
                    .accountNonExpired(true).build());
            return ResponseEntity.ok(String.format("User %s registered", registerSystemUserDto.getUsername()));
        }else {
            return ResponseEntity.badRequest().body(String.format("User %s already exists", registerSystemUserDto.getUsername()));
        }
    }
}
