package com.wcobq.ankibot.Anki.controller;

import com.wcobq.ankibot.Anki.model.UserWordDao;
import com.wcobq.ankibot.Anki.service.interfaces.AnkiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class AnkiController {
    private final AnkiService ankiService;

    @PostMapping
    private ResponseEntity<?> addWord(UserWordDao userWordDao) {
        return null;
    }
}
