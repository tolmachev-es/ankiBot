package com.wcobq.ankibot.User.mapper;

import com.wcobq.ankibot.User.model.User;
import com.wcobq.ankibot.User.repository.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User fromEntity(UserEntity user);
    UserEntity toEntity(User user);
}
