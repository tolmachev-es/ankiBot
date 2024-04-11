package org.wcobq.AnkiServ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.mapper.UserMapper;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;
import org.wcobq.dao.User;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.service.interfaces.AnkiService;
import org.wcobq.AnkiServ.service.interfaces.TranslateService;
import org.wcobq.AnkiServ.service.interfaces.UserService;
import org.wcobq.AnkiServ.service.interfaces.UserWordService;


@Service
@RequiredArgsConstructor
public class AnkiServiceImpl implements AnkiService {
    private final UserService userService;
    private final TranslateService translateService;
    private final UserWordService userWordService;

    @Override
    public Quiz getQuiz(Long userId) {
        return userWordService.getQuizWord(userService.getUserEntity(userId));
    }

    @Override
    public NewWordDao addWord(NewWordDao newWordDao) {
        TranslateEntity translate = translateService.addWord(newWordDao.getWord());
        User user = userService.checkUser(Long.valueOf(newWordDao.getUserId()));
        userWordService.createUserWord(translate, UserMapper.USER_MAPPER.toEntity(user));
        return newWordDao;
    }

    @Override
    public void checkAnswer(Long userId, String ruWord, String engWord) {
        UserEntity user = userService.getUserEntity(userId);
        userWordService.getAnswer(user, ruWord, engWord);
    }
}
