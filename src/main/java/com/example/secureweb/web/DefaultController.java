package com.example.secureweb.web;

import com.example.secureweb.model.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
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
}
