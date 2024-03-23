package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

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
    @OneToMany(mappedBy = "engWord")
    private Set<RuWordTranslateEntity> translateEntity;
}
