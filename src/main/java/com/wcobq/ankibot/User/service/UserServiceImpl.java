package com.wcobq.ankibot.User.service;

import com.wcobq.ankibot.User.mapper.UserMapper;
import com.wcobq.ankibot.User.model.User;
import com.wcobq.ankibot.User.repository.UserEntity;
import com.wcobq.ankibot.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Override
    public User getUser(Update update) {
        String username = getUsername(update);
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return UserMapper.USER_MAPPER.fromEntity(user.get());
        } else {
            return createUser(username);
        }
    }

    private User createUser(String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userRepository.save(userEntity);
        return UserMapper.USER_MAPPER.fromEntity(userEntity);
    }

    private String getUsername(Update update) {
        try {
            return update.getMessage().getChat().getUserName();
        } catch (Exception e) {
            return update.getCallbackQuery().getMessage().getChat().getUserName();
        }
    }
}
