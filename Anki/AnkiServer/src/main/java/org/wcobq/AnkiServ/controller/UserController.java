package com.wcobq.ankibot.Anki.controller;

import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    private ResponseEntity<?> createUser(User user) {
        userService.createUser(user);
        return null;
    }
}
