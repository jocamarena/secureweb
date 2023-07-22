package com.example.secureweb;

import com.example.secureweb.model.users.SystemAuthority;
import com.example.secureweb.model.users.SystemUser;
import com.example.secureweb.repositories.SystemUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SecurewebApplication {
    private Logger logger = LoggerFactory.getLogger(SecurewebApplication.class);

    private BCryptPasswordEncoder passwordEncoder;
    private SystemUserRepository systemUserRepository;
    public SecurewebApplication(BCryptPasswordEncoder passwordEncoder, SystemUserRepository systemUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.systemUserRepository = systemUserRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SecurewebApplication.class, args);
    }
    @Bean
    public CommandLineRunner runner() {
        return args -> {
            systemUserRepository.findByUsername("jcamarena").ifPresentOrElse(
                    user -> {
                        logger.info("User already exists");
                    },
                    () -> {
                        logger.info("Creating user");
                        systemUserRepository.save( SystemUser.builder()
                                .username("jcamarena")
                                .password(passwordEncoder.encode("Clairdel803!"))
                                        .accountNonExpired(true)
                                        .enabled(true)
                                        .accountNonLocked(true)
                                        .credentialsNonExpired(true)
                                .authorities(Set.of(SystemAuthority.builder()
                                        .authority("ADMIN").build()))
                                .build());
                    }
            );

        };
    }
}
