package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.model.Quiz;
import com.wcobq.ankibot.Anki.repository.EngWordRepository;
import com.wcobq.ankibot.Anki.repository.UserWordRepository;
import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import com.wcobq.ankibot.Anki.repository.entities.UserEntity;
import com.wcobq.ankibot.Anki.repository.entities.UserWordEntity;
import com.wcobq.ankibot.Anki.service.interfaces.UserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserWordServiceImpl implements UserWordService {
    private final UserWordRepository userWordRepository;
    private final EngWordRepository engWordRepository;

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
        UserWordEntity userWord = userWordRepository.getByUserOrderByCountAsc(user);
        Quiz quiz = Quiz.builder()
                .ruWord(userWord.getWord().getRuWord())
                .build();
        quiz.addRandomWord(getRandomWords(userWord));
        return quiz;
    }

    private List<String> getRandomWords(UserWordEntity userWord) {
        List<String> engWords = new ArrayList<>();
        engWords.add(userWord.getWord().getWordEntities().getFirst().getEngWord());
        engWords.addAll(engWordRepository.getThreeWord(userWord.getWord().getId())
                .stream()
                .map(EngWordEntity::getEngWord)
                .toList());
        return engWords;
    }
}
