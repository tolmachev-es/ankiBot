package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.service.interfaces.AnkiService;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateService;
import com.wcobq.ankibot.Anki.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnkiServiceImpl implements AnkiService {

    private final UserService userService;
    private final TranslateService translateService;

    @Override
    public List<SendMessage> getMenu(Update update) {
        User user = userService.checkUser(userSender(update));
        translateService.addWord(user, getText(update));
        return null;
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

    private Long getChatId(Update update) {
        try {
            return update.getMessage().getChatId();
        } catch (Exception e) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
    }

    private String getText(Update update) {
        try {
            return update.getMessage().getText();
        } catch (Exception e) {
            return update.getCallbackQuery().getMessage().getText();
        }
    }

    private User userSender(Update update){
        return User.builder()
                .id(getUserID(update))
                .username(getUsername(update))
                .build();
    }

}
