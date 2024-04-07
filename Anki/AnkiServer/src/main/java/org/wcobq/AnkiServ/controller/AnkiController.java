package org.wcobq.AnkiServ.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wcobq.dao.NewWordDao;
import org.wcobq.AnkiServ.service.interfaces.AnkiService;
import org.wcobq.dao.Quiz;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AnkiController {
    private final AnkiService ankiService;

    @PostMapping("/words")
    private ResponseEntity<NewWordDao> addWord(@RequestBody NewWordDao newWordDao) {
        return new ResponseEntity<>(ankiService.addWord(newWordDao), HttpStatus.CREATED);
    }

    @GetMapping("/quiz/{userId}")
    private ResponseEntity<Quiz> getQuiz(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(ankiService.getQuiz(userId), HttpStatus.OK);
    }

    @PostMapping("/quiz/{ruWord}/answer/{engWord}")
    private ResponseEntity<?> answerQuiz() {
        return null;
    }
}
