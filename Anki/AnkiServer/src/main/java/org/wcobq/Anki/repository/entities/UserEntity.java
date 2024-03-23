package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(schema = "PUBLIC", name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
}
