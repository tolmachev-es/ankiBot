package org.wcobq.AnkiServ.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.wcobq.AnkiServ.repository.entities.RuWordTranslateEntity;

import java.util.List;
import java.util.Optional;

public interface RuWordTranslateRepository extends JpaRepository<RuWordTranslateEntity, Long> {
    @Query(value = "select e from RuWordTranslateEntity as e where e.engWord.id != :eng_word_id order by function('RANDOM') ")
    List<RuWordTranslateEntity> findAllByEngWord_Id(@Param("eng_word_id") Long engWordId, Pageable pageable);

    Optional<RuWordTranslateEntity> findByRuWord_RuWordAndEngWord_EngWord(String ruWord, String engWord);

}
