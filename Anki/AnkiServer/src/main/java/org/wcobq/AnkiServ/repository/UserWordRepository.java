package org.wcobq.AnkiServ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcobq.AnkiServ.repository.entities.UserEntity;
import org.wcobq.AnkiServ.repository.entities.UserWordEntity;

public interface UserWordRepository extends JpaRepository<UserWordEntity, Long> {
    UserWordEntity getFirstByUserOrderByCountAsc(UserEntity user);
}
