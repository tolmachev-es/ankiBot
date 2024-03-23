package com.wcobq.ankibot.Anki.service.interfaces;

import com.wcobq.ankibot.Anki.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User checkUser(User user);

    User createUser(User user);
}
