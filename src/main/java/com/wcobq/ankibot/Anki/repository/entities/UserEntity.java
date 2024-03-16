package com.wcobq.ankibot.Anki.repository.entities;

import jakarta.persistence.*;
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
