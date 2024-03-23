package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.repository.EngWordRepository;
import com.wcobq.ankibot.Anki.repository.RuWordTranslateRepository;
import com.wcobq.ankibot.Anki.repository.TranslateRepository;
import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import com.wcobq.ankibot.Anki.repository.entities.RuWordKey;
import com.wcobq.ankibot.Anki.repository.entities.RuWordTranslateEntity;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateSender;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslateServiceImpl implements TranslateService {
    private final TranslateRepository translateRepository;
    private final EngWordRepository engWordRepository;
    private final TranslateSender translateSender;
    private final RuWordTranslateRepository ruWordTranslateRepository;

    @Override
    public TranslateEntity addWord(String word) {
        return getWords(word);
    }

    @Override
    public SendMessage getQuiz(User user) {
        return null;
    }

    private TranslateEntity getWords(String word) {
        String newWord = word.toLowerCase();
        Optional<TranslateEntity> getWord = translateRepository.findByRuWord(newWord);
        return getWord.orElseGet(() -> createTranslate(newWord));
    }

    private TranslateEntity createTranslate(String word) {
        TranslateEntity translateEntity = TranslateEntity.builder().ruWord(word).build();
        try {
            translateRepository.save(translateEntity);
        } catch (Exception e) {
            return null;
        }
        List<EngWordEntity> engWordEntities = translateSender.getTranslate(translateEntity);
        if (!engWordEntities.isEmpty()) {
            engWordRepository.saveAll(engWordEntities);
            TranslateEntity translate = translateRepository.save(translateEntity);
            List<RuWordTranslateEntity> ruWordTranslateEntities = engWordEntities.stream()
                    .map(ew -> RuWordTranslateEntity.builder()
                            .id(new RuWordKey(translate.getId(), ew.getId()))
                            .ruWord(translate)
                            .engWord(ew)
                            .build())
                    .collect(Collectors.toList());
            ruWordTranslateRepository.saveAll(ruWordTranslateEntities);
        }
        return translateEntity;
    }
}
