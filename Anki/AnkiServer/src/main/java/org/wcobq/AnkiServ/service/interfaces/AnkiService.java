package org.wcobq.AnkiServ.service.interfaces;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;

@Service
public interface AnkiService {
    Quiz getQuiz(Long userId);

    NewWordDao addWord(NewWordDao newWordDao);

    void checkAnswer(Long userId, String ruWord, String engWord);
}
