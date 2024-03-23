package com.wcobq.ankibot.Anki.service.interfaces;

import com.wcobq.ankibot.Anki.model.Quiz;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import com.wcobq.ankibot.Anki.repository.entities.UserEntity;


public interface UserWordService {
    void createUserWord(TranslateEntity translate, UserEntity user);

    Quiz getQuizWord(UserEntity user);
}
