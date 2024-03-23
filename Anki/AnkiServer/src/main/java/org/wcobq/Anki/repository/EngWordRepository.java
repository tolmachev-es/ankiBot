package com.wcobq.ankibot.Anki.repository;

import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EngWordRepository extends JpaRepository<EngWordEntity, Long> {
    Set<EngWordEntity> findAllByEngWord(String engWord);

}
