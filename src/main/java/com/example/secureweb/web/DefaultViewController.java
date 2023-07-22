package com.example.secureweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultViewController {
    @GetMapping("/index")
    public String home() {
        return "index";
    }
    @GetMapping("/secure/index")
    public String homeSecure() {
            return "secure";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
