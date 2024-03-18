package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "PUBLIC", name = "ENG_WORDS")
public class EngWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "ENG_WORD")
    private String engWord;
    @ManyToMany
    @JoinTable(
            name = "RU_WORD_TRANSLATE",
            joinColumns = @JoinColumn(name = "ENG_WORD_ID"),
            inverseJoinColumns = @JoinColumn(name = "RU_WORD_ID")
    )
    private List<TranslateEntity> translateEntity = new ArrayList<>();
}
