package com.wcobq.ankibot.Anki.repository;

import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TranslateRepository extends JpaRepository<TranslateEntity, Long> {
    Optional<TranslateEntity> findByRuWord(String ruWord);
}
