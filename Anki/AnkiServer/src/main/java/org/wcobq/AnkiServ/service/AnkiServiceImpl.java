package org.wcobq.AnkiServ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.wcobq.AnkiServ.mapper.UserMapper;
import org.wcobq.AnkiServ.model.Quiz;
import org.wcobq.dao.User;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.service.interfaces.AnkiService;
import org.wcobq.AnkiServ.service.interfaces.TranslateService;
import org.wcobq.AnkiServ.service.interfaces.UserService;
import org.wcobq.AnkiServ.service.interfaces.UserWordService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnkiServiceImpl implements AnkiService {

    private final UserService userService;
    private final TranslateService translateService;
    private final UserWordService userWordService;

    @Override
    public void getMenu(Update update) {
        Quiz quiz = userWordService.getQuizWord(
                UserMapper.USER_MAPPER.toEntity(
                        userService.checkUser(
                                userSender(update))));
    }

    @Override
    public void addWord(Update update) {
        List<SendMessage> sendMessages = new ArrayList<>();
        User user = userService.checkUser(userSender(update));
        TranslateEntity translate = translateService.addWord(getText(update));
        userWordService.createUserWord(translate, UserMapper.USER_MAPPER.toEntity(user));
        sendMessages.add(createAddWordMessage(getChatId(update), translate));
    }

    private String getUsername(Update update) {
        try {
            return update.getMessage().getChat().getUserName();
        } catch (Exception e) {
            return update.getCallbackQuery().getMessage().getChat().getUserName();
        }
    }


    private Long getUserID(Update update) {
        try {
            return update.getMessage().getChat().getId();
        } catch (Exception e) {
            return update.getCallbackQuery().getMessage().getChat().getId();
        }
    }

    private String getChatId(Update update) {
        try {
            return String.valueOf(update.getMessage().getChatId());
        } catch (Exception e) {
            return String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        }
    }

    private String getText(Update update) {
        try {
            return update.getMessage().getText();
        } catch (Exception e) {
            return update.getCallbackQuery().getMessage().getText();
        }
    }

    private User userSender(Update update) {
        return User.builder()
                .id(getUserID(update))
                .username(getUsername(update))
                .build();
    }

    private SendMessage createAddWordMessage(String chatId, TranslateEntity translate) {
        String text = String.format("Слово '%s' успешно добавлено", translate.getRuWord());
        return new SendMessage(String.valueOf(chatId), text);
    }
}
