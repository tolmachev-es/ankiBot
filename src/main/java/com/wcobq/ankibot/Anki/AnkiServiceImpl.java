package com.wcobq.ankibot.Anki;

import com.wcobq.ankibot.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class AnkiServiceImpl implements AnkiService{

    private UserService userService;

    @Override
    public SendMessage getMenu(Update update) {
        userService.getUser(update);
        return null;
    }
}
