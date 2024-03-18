package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
public class RuWordId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "RU_WORD_ID", nullable = false)
    private TranslateEntity ruWord;
    @ManyToOne
    @JoinColumn(name = "ENG_WORD_ID", nullable = false)
    private EngWordEntity engWord;
}
