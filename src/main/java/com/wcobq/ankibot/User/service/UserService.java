package com.wcobq.ankibot.User.service;

import com.wcobq.ankibot.User.model.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface UserService {
    User getUser(Update update);
}
