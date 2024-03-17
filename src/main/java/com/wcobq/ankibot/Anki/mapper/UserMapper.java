package com.wcobq.ankibot.Anki.mapper;

import com.wcobq.ankibot.Anki.model.User;
import com.wcobq.ankibot.Anki.repository.entities.UserEntity;
import com.wcobq.ankibot.Anki.sender.reverso.response.ReversoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User fromEntity(UserEntity user);
    UserEntity toEntity(User user);

}
