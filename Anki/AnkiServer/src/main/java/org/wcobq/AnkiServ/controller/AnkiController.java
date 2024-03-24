package org.wcobq.AnkiServ.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcobq.dao.User;
import org.wcobq.AnkiServ.model.UserWordDao;
import org.wcobq.AnkiServ.service.interfaces.AnkiService;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AnkiController {
    private final AnkiService ankiService;

    @PostMapping("/words")
    private ResponseEntity<?> addWord(UserWordDao userWordDao) {
        return null;
    }

    @GetMapping("/quiz")
    private ResponseEntity<?> getQuiz(User user) {
        return null;
    }

    @PostMapping("/quiz/{ruWord}/answer/{engWord}")
    private ResponseEntity<?> answerQuiz() {
        return null;
    }
}
