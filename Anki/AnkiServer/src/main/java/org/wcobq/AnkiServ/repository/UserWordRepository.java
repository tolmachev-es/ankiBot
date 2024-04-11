package org.wcobq.AnkiServ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.repository.entities.UserWordEntity;

import java.util.Optional;

public interface UserWordRepository extends JpaRepository<UserWordEntity, Long> {
    UserWordEntity getFirstByUserOrderByCountAsc(UserEntity user);
    Optional<UserWordEntity> getByUserAndWord(UserEntity user, TranslateEntity word);
    Optional<UserWordEntity> getByUser_IdAndWord_Id(Long userId, Long wordId);
}
