package org.wcobq.AnkiServ.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.wcobq.AnkiServ.model.User;
import org.wcobq.AnkiServ.repository.entities.UserEntity;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User fromEntity(UserEntity user);

    UserEntity toEntity(User user);

}
