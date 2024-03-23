package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.mapper.UserMapper;
import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.repository.UserRepository;
import com.wcobq.ankibot.Anki.repository.entities.UserEntity;
import com.wcobq.ankibot.Anki.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
