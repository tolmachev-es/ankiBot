package org.wcobq.AnkiServ.service.interfaces;

import org.wcobq.AnkiServ.model.Quiz;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.repository.entities.UserWordEntity;

public interface UserWordService {
    UserWordEntity createUserWord(TranslateEntity translate, UserEntity user);

    Quiz getQuizWord(UserEntity user);
}
