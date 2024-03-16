package com.wcobq.ankibot.Anki.service.interfaces;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public interface AnkiService {
    List<SendMessage> getMenu(Update update);
}
