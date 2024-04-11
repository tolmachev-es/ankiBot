package org.wcobq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.wcobq.clients.ServerClient;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;
import org.wcobq.dao.User;
import org.wcobq.repositories.RedisTemplateUser;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BotService {
    private final ServerClient client;
    private final RedisTemplateUser userS;

    public List<SendMessage> menu(Update update) {
        List<SendMessage> messages = new ArrayList<>();
        String text = getText(update);
        Long userId = getUserID(update);
        String parameter = userS.get(String.valueOf(userId));
        if (parameter != null) {
            if (text.equals("MENU")) {
                messages.add(getMenu(update, userId));
            } else if (parameter.equals("ADD_WORD")) {
                if (update.getCallbackQuery() == null) {
                    messages.add(addWord(update));
                    messages.add(getMenu(update, userId));
                }
            } else if (parameter.equals("QUIZ")) {
                messages.add(checkAnswer(update));
                messages.add(getQuizMessage(update, userId));
            }
        } else {
            if (text.equals("/start")) {
                messages.add(createUser(update));
                messages.add(getMenuMessage(getChatId(update)));
            } else if (text.equals("ADD_NEW_WORD")) {
                messages.add(getAddWordMessage(update));
            } else if (text.equals("GET_QUIZ")) {
                messages.add(getQuizMessage(update, userId));
            }
        }
        return messages;
    }

    private SendMessage checkAnswer(Update update) {
        String[] result = getText(update).split("==");
        Boolean answer = client.patchQuizAnswer(getUserID(update), result[0], result[1]);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId(update));
        if (answer) {
            sendMessage.setText("Правильно");
        } else {
            sendMessage.setText("Попробуй еще раз");
        }
        return sendMessage;
    }

    public SendMessage createUser(Update update) {
        Long userId = getUserID(update);
        User user = User.builder().id(userId).username(getUsername(update)).build();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId(update));
        try {
            User newUser = client.createNewUser(user);
            sendMessage.setText(String.format("Добро пожаловать в Anki Bot, %s", newUser.getUsername()));
        } catch (ClassCastException e) {
            sendMessage.setText("Произошла ошибка при создании учетной записи");
        }
        return sendMessage;
    }

    public SendMessage addWord(Update update) {
        NewWordDao newWordDao = NewWordDao.builder()
                .userId(String.valueOf(getUserID(update)))
                .word(getText(update)).build();
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(getChatId(update));
        try {
            NewWordDao createdWord = client.addNewWord(newWordDao);
            sendMessage.setText(String.format("Добавлено новое слово %s", createdWord.getWord()));
            return sendMessage;
        } catch (ClassCastException e) {
            sendMessage.setText("Произошла ошибка при добавлении слова");
        }
        return sendMessage;

    }

    private SendMessage getMenu(Update update, Long userId) {
        userS.delete(String.valueOf(userId));
        return getMenuMessage(getChatId(update));
    }

    private String getUsername(Update update) {
        try {
            return update.getMessage().getFrom().getUserName();
        } catch (Exception e) {
            return update.getCallbackQuery().getFrom().getUserName();
        }
    }


    private Long getUserID(Update update) {
        if (update.getCallbackQuery() != null) {
            return update.getCallbackQuery().getFrom().getId();
        } else {
            return update.getMessage().getFrom().getId();
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
            return update.getCallbackQuery().getData();
        }
    }

    private SendMessage getMenuMessage(String chatIdString) {
        Long chatId = Long.parseLong(chatIdString);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Меню");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        InlineKeyboardButton addWord = new InlineKeyboardButton();
        addWord.setText("Добавить слово в словарь");
        addWord.setCallbackData("ADD_NEW_WORD");
        buttons.add(List.of(addWord));
        InlineKeyboardButton getQuiz = new InlineKeyboardButton();
        getQuiz.setText("Повторить слова");
        getQuiz.setCallbackData("GET_QUIZ");
        buttons.add(List.of(getQuiz));
        markup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    private SendMessage getAddWordMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId(update));
        userS.save(String.valueOf(getUserID(update)), "ADD_WORD");
        sendMessage.setText("Введите слово на русском которое хотите добавить");
        return sendMessage;
    }

    private SendMessage getQuizMessage(Update update, Long userId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId(update));
        userS.save(String.valueOf(userId), "QUIZ");
        Quiz quiz = client.getQuizFromServer(getUserID(update));
        sendMessage.setText(quiz.getRuWord());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (String engWord : quiz.getEngWords()) {
            InlineKeyboardButton inlineKeyboardButton = InlineKeyboardButton.builder()
                    .text(engWord)
                    .callbackData(quiz.getRuWord() + "==" + engWord)
                    .build();
            buttons.add(List.of(inlineKeyboardButton));
        }
        InlineKeyboardButton inlineKeyboardButton = InlineKeyboardButton.builder()
                .text("MENU")
                .callbackData("MENU")
                .build();
        buttons.add(List.of(inlineKeyboardButton));
        markup.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }
}
