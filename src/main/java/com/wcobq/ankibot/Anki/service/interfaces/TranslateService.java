package com.wcobq.ankibot.Anki.service.interfaces;

import com.wcobq.ankibot.Anki.model.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public interface TranslateService {
    SendMessage addWord(User user, String word);
    SendMessage getQuiz(User user);
}
