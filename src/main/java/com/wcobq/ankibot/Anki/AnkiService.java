package com.wcobq.ankibot.Anki;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface AnkiService {
    SendMessage getMenu(Update update);
}
