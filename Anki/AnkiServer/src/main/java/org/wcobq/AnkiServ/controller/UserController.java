package org.wcobq.AnkiServ.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcobq.AnkiServ.model.User;
import org.wcobq.AnkiServ.service.interfaces.UserService;

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
