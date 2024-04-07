package org.wcobq.repositories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Data
@NoArgsConstructor
@Table(schema = "PUBLIC", name = "USERS")
public class UserStatusEntity {
    @Id
    @Column(name = "ID")
    private Long userId;
    @Column(name = "ADD_WORD")
    private Boolean addWord;
    @Column(name = "GET_QUIZ")
    private Boolean getQuiz;
}
