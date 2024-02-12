package com.wcobq.ankibot.User.service;

import com.wcobq.ankibot.User.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserService {
    User getUser(Update update);
}
