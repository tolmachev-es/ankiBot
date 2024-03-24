package org.wcobq.AnkiServ.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "RU_WORD_TRANSLATE")
@Entity
public class RuWordTranslateEntity {
    @EmbeddedId
    private RuWordKey id;
    @ManyToOne
    @MapsId("ruWordId")
    @JoinColumn(name = "RU_WORD_ID", nullable = false)
    private TranslateEntity ruWord;
    @ManyToOne
    @MapsId("engWordId")
    @JoinColumn(name = "ENG_WORD_ID", nullable = false)
    private EngWordEntity engWord;
}
