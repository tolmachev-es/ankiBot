package org.wcobq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.wcobq.clients.ServerClient;
import org.wcobq.dao.User;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BotService {
    private final ServerClient client;
    private final ObjectMapper objectMapper;

    public List<SendMessage> menu(Update update) {
        List<SendMessage> messages = new ArrayList<>();
        if (getText(update).equals("/start")) {
            messages.add(createUser(update));
        }
        return messages;
    }

    public SendMessage createUser(Update update) {
        User user = User.builder().id(getUserID(update)).username(getUsername(update)).build();
        try {
            User newUser = objectMapper.convertValue(client.createNewUser(user), User.class);
            SendMessage sendMessage = new SendMessage(getChatId(update), String.format("Добро пожаловать в Anki Bot"));
            return sendMessage;
        } catch (ClassCastException e) {
            return null;
        }
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
}
