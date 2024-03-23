package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "USER_WORDS")
public class UserWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WORD_ID")
    private TranslateEntity word;
    @Column(name = "REPEAT_COUNT", nullable = false)
    private Long count;
    @Column(name = "IT_STUDY", nullable = false)
    private boolean isStudy;
}
