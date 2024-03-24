package org.wcobq.AnkiServ.service.interfaces;

import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.model.User;

@Service
public interface UserService {
    User checkUser(User user);

    User createUser(User user);
}
