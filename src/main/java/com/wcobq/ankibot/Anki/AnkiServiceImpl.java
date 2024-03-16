package com.wcobq.ankibot.Anki;

import com.wcobq.ankibot.User.model.User;
import com.wcobq.ankibot.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class AnkiServiceImpl implements AnkiService{

    private final UserService userService;

    @Override
    public SendMessage getMenu(Update update) {
        User user = userService.getUser(update);
        return null;
    }

    public SendMessage setCommand(Update update) {
        return null;
    }
}
