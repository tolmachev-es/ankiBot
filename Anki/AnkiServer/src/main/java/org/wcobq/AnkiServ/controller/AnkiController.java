package org.wcobq.AnkiServ.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcobq.AnkiServ.model.UserWordDao;
import org.wcobq.AnkiServ.service.interfaces.AnkiService;

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
