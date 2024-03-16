package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "ENG_WORDS")
public class EngWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "RU_WORD_ID", nullable = false)
    private Long ruWordId;
    @Column(name = "ENG_WORD")
    private String engWord;
}
