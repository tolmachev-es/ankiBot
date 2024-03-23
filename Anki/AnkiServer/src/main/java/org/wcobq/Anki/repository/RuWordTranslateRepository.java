package com.wcobq.ankibot.Anki.repository;

import com.wcobq.ankibot.Anki.repository.entities.RuWordTranslateEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RuWordTranslateRepository extends JpaRepository<RuWordTranslateEntity, Long> {
    @Query(value = "select e from RuWordTranslateEntity as e where e.engWord.id != :eng_word_id order by function('RANDOM') ")
    List<RuWordTranslateEntity> findAllByEngWord_Id(@Param("eng_word_id") Long engWordId, Pageable pageable);
}
