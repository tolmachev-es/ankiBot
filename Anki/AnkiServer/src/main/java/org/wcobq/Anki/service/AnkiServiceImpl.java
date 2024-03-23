package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.mapper.UserMapper;
import com.wcobq.ankibot.Anki.model.Quiz;
import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import com.wcobq.ankibot.Anki.service.interfaces.AnkiService;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateService;
import com.wcobq.ankibot.Anki.service.interfaces.UserService;
import com.wcobq.ankibot.Anki.service.interfaces.UserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

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
