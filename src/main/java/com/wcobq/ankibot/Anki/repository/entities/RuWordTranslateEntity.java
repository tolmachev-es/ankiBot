package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "RU_WORD_TRANSLATE")
@Entity
public class RuWordTranslateEntity implements Serializable {
    @EmbeddedId
    private RuWordId id;
}
