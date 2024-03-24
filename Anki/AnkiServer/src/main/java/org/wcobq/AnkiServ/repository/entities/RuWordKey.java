package org.wcobq.AnkiServ.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuWordKey implements Serializable {
    @Column(name = "RU_WORD_ID", nullable = false)
    private Long ruWordId;
    @Column(name = "ENG_WORD_ID", nullable = false)
    private Long engWordId;
}
