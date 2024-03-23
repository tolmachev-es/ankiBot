package com.wcobq.ankibot.Anki.repository;

import com.wcobq.ankibot.Anki.repository.entities.UserEntity;
import com.wcobq.ankibot.Anki.repository.entities.UserWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWordRepository extends JpaRepository<UserWordEntity, Long> {
    UserWordEntity getFirstByUserOrderByCountAsc(UserEntity user);
}
