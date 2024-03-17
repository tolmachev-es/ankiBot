package com.wcobq.ankibot.Anki.repository;

import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngWordRepository extends JpaRepository<EngWordEntity, Long> {
}
