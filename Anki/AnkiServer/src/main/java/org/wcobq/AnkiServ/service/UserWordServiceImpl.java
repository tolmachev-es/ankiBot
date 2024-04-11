package org.wcobq.AnkiServ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.repository.EngWordRepository;
import org.wcobq.AnkiServ.repository.RuWordTranslateRepository;
import org.wcobq.AnkiServ.repository.UserWordRepository;
import org.wcobq.AnkiServ.repository.entities.RuWordTranslateEntity;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.repository.entities.UserWordEntity;
import org.wcobq.AnkiServ.service.interfaces.UserWordService;
import org.wcobq.dao.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserWordServiceImpl implements UserWordService {
    private final UserWordRepository userWordRepository;
    private final EngWordRepository engWordRepository;
    private final RuWordTranslateRepository ruWordTranslateRepository;

    @Override
    public UserWordEntity createUserWord(TranslateEntity translate, UserEntity user) {
        Optional<UserWordEntity> hasWordEntity = userWordRepository.getByUser_IdAndWord_Id(user.getId(), translate.getId());
        if (hasWordEntity.isEmpty()) {
            UserWordEntity userWordEntity = UserWordEntity.builder()
                    .word(translate)
                    .user(user)
                    .count(0L)
                    .isStudy(true)
                    .build();
            userWordRepository.save(userWordEntity);
            return userWordEntity;
        } else {
            throw new RuntimeException();
        }
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

    @Override
    public void getAnswer(UserEntity user, String ruWord, String engWord) {
        Optional<RuWordTranslateEntity> answer = ruWordTranslateRepository
                .findByRuWord_RuWordAndEngWord_EngWord(ruWord, engWord);
        if (answer.isEmpty()) {
            throw new IncorrectAwswerException();
        } else {
            Optional<UserWordEntity> word = userWordRepository.getByUserAndWord(user, answer.get().getRuWord());
            if (word.isPresent()) {
                UserWordEntity getWord = word.get();
                getWord.setCount(getWord.getCount() + 1L);
                userWordRepository.save(getWord);
            } else {
                throw new IncorrectAwswerException();
            }
        }
    }

    private List<String> getRandomWords(UserWordEntity userWord) {
        List<String> engWords = new ArrayList<>();
        engWords.add(userWord.getWord().getWordEntities().getFirst().getEngWord().getEngWord());
        List<RuWordTranslateEntity> ruWordTranslateEntities = ruWordTranslateRepository.findAllByEngWord_Id(userWord.getId(), PageRequest.of(0, 3));
        engWords.addAll(ruWordTranslateEntities.stream().map(e -> e.getEngWord().getEngWord()).toList());
        return engWords;
    }
}
