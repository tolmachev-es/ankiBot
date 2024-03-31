package org.wcobq.AnkiServ.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.User;
import org.wcobq.AnkiServ.service.interfaces.AnkiService;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AnkiController {
    private final AnkiService ankiService;

    @PostMapping("/words")
    private ResponseEntity<?> addWord(NewWordDao newWordDao) {
        return new ResponseEntity<>(ankiService.addWord(newWordDao), HttpStatus.CREATED);
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
