package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(schema = "PUBLIC", name = "TRANSLATES")
public class TranslateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "RU_WORD", nullable = false)
    private String ruWord;
    @ManyToMany
    @JoinTable(
            name = "RU_WORD_TRANSLATE",
            joinColumns = @JoinColumn(name = "RU_WORD_ID"),
            inverseJoinColumns = @JoinColumn(name = "ENG_WORD_ID")
    )
    private List<EngWordEntity> wordEntities = new ArrayList<>();
}
