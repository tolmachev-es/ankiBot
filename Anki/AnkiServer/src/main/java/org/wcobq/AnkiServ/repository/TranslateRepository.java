package org.wcobq.AnkiServ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;

import java.util.Optional;

public interface TranslateRepository extends JpaRepository<TranslateEntity, Long> {
    Optional<TranslateEntity> findByRuWord(String ruWord);
}
