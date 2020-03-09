package com.mapfap.spez.controller;

import com.mapfap.spez.model.User;
import com.mapfap.spez.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    Iterable<User> getUsers() {
        return userService.getUsers();
    }
}
