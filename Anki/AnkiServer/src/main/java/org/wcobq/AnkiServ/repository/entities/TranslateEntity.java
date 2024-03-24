package org.wcobq.AnkiServ.repository.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
    @OneToMany(mappedBy = "ruWord", fetch = FetchType.EAGER)
    private List<RuWordTranslateEntity> wordEntities;
}
