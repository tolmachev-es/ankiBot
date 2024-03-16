package com.wcobq.ankibot.Anki.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class User {
    private Long id;
    private String username;
}
