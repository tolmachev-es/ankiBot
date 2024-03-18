package com.wcobq.ankibot.Anki.service.interfaces;

import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public interface TranslateService {
    TranslateEntity addWord(String word);

    SendMessage getQuiz(User user);
}
