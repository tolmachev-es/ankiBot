package org.wcobq.AnkiServ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.mapper.UserMapper;
import org.wcobq.AnkiServ.model.User;
import org.wcobq.AnkiServ.repository.UserRepository;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.service.interfaces.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User checkUser(User user) {
        if (getUser(user)) {
            return user;
        } else {
            return createUser(user);
        }
    }

    public boolean getUser(User user) {
        Optional<UserEntity> getUser = userRepository.findById(user.getId());
        if (getUser.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.USER_MAPPER.toEntity(user);
        userRepository.save(userEntity);
        return UserMapper.USER_MAPPER.fromEntity(userEntity);
    }

}
