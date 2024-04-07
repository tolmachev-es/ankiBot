package org.wcobq.AnkiServ.service.interfaces;

import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.dao.User;

@Service
public interface UserService {
    User checkUser(Long userId);

    User createUser(User user);

    UserEntity getUserEntity(Long userId);
}
