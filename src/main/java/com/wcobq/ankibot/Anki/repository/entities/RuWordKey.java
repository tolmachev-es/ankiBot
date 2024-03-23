package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RuWordKey implements Serializable {
    @Column(name = "RU_WORD_ID", nullable = false)
    private Long ruWordId;
    @Column(name = "ENG_WORD_ID", nullable = false)
    private Long engWordId;
}
