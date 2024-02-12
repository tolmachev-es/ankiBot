package com.wcobq.ankibot.User.repository;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(schema = "PUBLIC", name = "USER")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
}
