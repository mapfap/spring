package com.mapfap.spez.service;

import com.mapfap.spez.model.User;
import com.mapfap.spez.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
