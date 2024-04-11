package org.wcobq.AnkiServ.service.interfaces;

import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.repository.entities.UserWordEntity;
import org.wcobq.dao.Quiz;

public interface UserWordService {
    UserWordEntity createUserWord(TranslateEntity translate, UserEntity user);

    Quiz getQuizWord(UserEntity user);

    void getAnswer(UserEntity user, String ruWord, String engWord);
}
