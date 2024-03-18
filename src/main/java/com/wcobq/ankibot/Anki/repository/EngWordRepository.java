package com.wcobq.ankibot.Anki.repository;

import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EngWordRepository extends JpaRepository<EngWordEntity, Long> {
    @Query(value = "select e from EngWordEntity e " +
            "inner join RuWordTranslateEntity rw " +
            "on e.id = rw.engWord.id where rw.ruWord != :id", nativeQuery = true)
    List<EngWordEntity> getThreeWord(@Param("id") Long id);
}
