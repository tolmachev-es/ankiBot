package org.wcobq;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.wcobq.clients.ServerClient;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;
import org.wcobq.dao.User;
import org.wcobq.repositories.UserStatusEntity;
import org.wcobq.repositories.UserStatusRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotService {
    private final ServerClient client;
    private final ObjectMapper objectMapper;
    private final UserStatusRepository userStatusRepository;

    public List<SendMessage> menu(Update update) {
        List<SendMessage> messages = new ArrayList<>();
        String text = getText(update);
        if (text.equals("/start")) {
            messages.add(createUser(update));
            messages.add(getMenuMessage(getChatId(update)));
        } else if (text.equals("ADD_NEW_WORD")) {
            messages.add(getAddWordMessage(update));
        } else if (text.equals("GET_QUIZ")) {
            messages.add(getQuizMessage(update));
        } else {
            messages.add(addWord(update));
            messages.add(getMenuMessage(getChatId(update)));
        }
        return messages;
    }

    public SendMessage createUser(Update update) {
        Long userId = getUserID(update);
        User user = User.builder().id(userId).username(getUsername(update)).build();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(getChatId(update));
        try {
            User newUser = objectMapper.convertValue(client.createNewUser(user), User.class);
            sendMessage.setText(String.format("Добро пожаловать в Anki Bot"));
            UserStatusEntity userStatusEntity = UserStatusEntity.builder().userId(userId)
                    .getQuiz(false)
                    .addWord(false)
                    .build();
            userStatusRepository.save(userStatusEntity);
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
            NewWordDao createdWord = objectMapper.convertValue(client.addNewWord(newWordDao), NewWordDao.class);
            sendMessage.setText(String.format("Добавлено новое слово %s", createdWord.getWord()));
            return sendMessage;
        } catch (ClassCastException e) {
            sendMessage.setText("Произошла ошибка при добавлении слова");
        }
        return sendMessage;
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
        Optional<UserStatusEntity> userStatusEntity = userStatusRepository.findById(getUserID(update));
        if (userStatusEntity.isPresent()) {
            userStatusEntity.get().setAddWord(true);
            userStatusRepository.save(userStatusEntity.get());
            sendMessage.setText("Введите слово на русском которое хотите добавить");
        } else {
            sendMessage.setText("Пользователь не найден");
        }
        return sendMessage;
    }

    private SendMessage getQuizMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(getChatId(update));
        Optional<UserStatusEntity> userStatusEntity = userStatusRepository.findById(getUserID(update));
        if (userStatusEntity.isPresent()) {
            userStatusEntity.get().setGetQuiz(true);
            userStatusRepository.save(userStatusEntity.get());
            Quiz quiz = client.getQuizFromServer(userStatusEntity.get().getUserId());
            sendMessage.setText(quiz.getRuWord());
            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            for (String engWord: quiz.getEngWords()) {
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
        } else {
            sendMessage.setText("Пользователь не найден");
        }
        return sendMessage;
    }
}
