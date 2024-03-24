package org.wcobq.AnkiServ.service.interfaces;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.wcobq.dao.User;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;

@Service
public interface TranslateService {
    TranslateEntity addWord(String word);

    SendMessage getQuiz(User user);
}
