package com.example.secureweb.security;

import com.example.secureweb.model.users.SystemUser;
import com.example.secureweb.repositories.SystemUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JpaUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
    private SystemUserRepository systemUserRepository;
    public JpaUserDetailsService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserDetails user = User.builder()
                .username(systemUser.getUsername())
                .password(systemUser.getPassword())
                .authorities(systemUser.getAuthorities())
                .build();
        return user;
    }
}
