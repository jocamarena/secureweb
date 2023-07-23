package com.example.secureweb.services;

import com.example.secureweb.model.users.SystemUser;
import com.example.secureweb.repositories.SystemUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SystemUserService {
    private SystemUserRepository systemUserRepository;
    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }
    public List<SystemUser> findAllUsers(){
        return systemUserRepository.findAll();
    }
}
