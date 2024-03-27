package org.wcobq.AnkiServ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.model.Quiz;
import org.wcobq.AnkiServ.repository.EngWordRepository;
import org.wcobq.AnkiServ.repository.RuWordTranslateRepository;
import org.wcobq.AnkiServ.repository.UserWordRepository;
import org.wcobq.AnkiServ.repository.entities.RuWordTranslateEntity;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.repository.entities.UserWordEntity;
import org.wcobq.AnkiServ.service.interfaces.UserWordService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWordServiceImpl implements UserWordService {
    private final UserWordRepository userWordRepository;
    private final EngWordRepository engWordRepository;
    private final RuWordTranslateRepository ruWordTranslateRepository;

    @Override
    public UserWordEntity createUserWord(TranslateEntity translate, UserEntity user) {
        UserWordEntity userWordEntity = UserWordEntity.builder()
                .word(translate)
                .user(user)
                .count(0L)
                .isStudy(true)
                .build();
        userWordRepository.save(userWordEntity);
        return userWordEntity;
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
        List<RuWordTranslateEntity> ruWordTranslateEntities = ruWordTranslateRepository.findAllByEngWord_Id(userWord.getId(), PageRequest.of(0, 3));
        engWords.addAll(ruWordTranslateEntities.stream().map(e -> e.getEngWord().getEngWord()).toList());
        return engWords;
    }
}
