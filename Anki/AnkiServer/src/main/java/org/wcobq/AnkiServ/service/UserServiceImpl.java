package org.wcobq.AnkiServ.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.mapper.UserMapper;
import org.wcobq.dao.User;
import org.wcobq.AnkiServ.repository.UserRepository;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.service.interfaces.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User checkUser(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        return user.map(UserMapper.USER_MAPPER::fromEntity).orElse(null);
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.USER_MAPPER.toEntity(user);
        userRepository.save(userEntity);
        return UserMapper.USER_MAPPER.fromEntity(userEntity);
    }

    @Override
    public UserEntity getUserEntity(Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

}
