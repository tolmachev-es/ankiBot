package org.wcobq.AnkiServ.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wcobq.AnkiServ.repository.entities.EngWordEntity;

import java.util.Set;

@Repository
public interface EngWordRepository extends JpaRepository<EngWordEntity, Long> {
    Set<EngWordEntity> findAllByEngWord(String engWord);

}
