package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.repository.EngWordRepository;
import com.wcobq.ankibot.Anki.repository.TranslateRepository;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateSender;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslateServiceImpl implements TranslateService {
    private final TranslateRepository translateRepository;
    private final EngWordRepository engWordRepository;
    private final TranslateSender translateSender;

    @Override
    public SendMessage addWord(User user, String word) {

        return null;
    }

    @Override
    public SendMessage getQuiz(User user) {
        return null;
    }

    private TranslateEntity getWords(String word) {
        Optional<TranslateEntity> getWord = translateRepository.findByRuWord(word);
        return getWord.orElseGet(() -> createTranslate(word));
    }

    private TranslateEntity createTranslate(String word) {
        return null;
    }


}
