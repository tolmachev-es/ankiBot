package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.model.Quiz;
import com.wcobq.ankibot.Anki.repository.EngWordRepository;
import com.wcobq.ankibot.Anki.repository.RuWordTranslateRepository;
import com.wcobq.ankibot.Anki.repository.UserWordRepository;
import com.wcobq.ankibot.Anki.repository.entities.*;
import com.wcobq.ankibot.Anki.service.interfaces.UserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWordServiceImpl implements UserWordService {
    private final UserWordRepository userWordRepository;
    private final EngWordRepository engWordRepository;
    private final RuWordTranslateRepository ruWordTranslateRepository;

    @Override
    public void createUserWord(TranslateEntity translate, UserEntity user) {
        UserWordEntity userWordEntity = UserWordEntity.builder()
                .word(translate)
                .user(user)
                .count(0L)
                .isStudy(true)
                .build();
        userWordRepository.save(userWordEntity);
    }

    @Override
    public Quiz getQuizWord(UserEntity user) {
        UserWordEntity userWord = userWordRepository.getFirstByUserOrderByCountAsc(user);
        Quiz quiz = Quiz.builder()
                .ruWord(userWord.getWord().getRuWord())
                .build();
        quiz.addRandomWord(getRandomWords(userWord));
        return quiz;
    }

    private List<String> getRandomWords(UserWordEntity userWord) {
        List<String> engWords = new ArrayList<>();
        engWords.add(userWord.getWord().getWordEntities().getFirst().getEngWord().getEngWord());
        List<RuWordTranslateEntity> ruWordTranslateEntities = ruWordTranslateRepository.findAllByEngWord_Id(userWord.getId(), PageRequest.of(0,3));
        engWords.addAll(ruWordTranslateEntities.stream().map(e -> e.getEngWord().getEngWord()).toList());
        return engWords;
    }
}