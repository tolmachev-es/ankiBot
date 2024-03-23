package com.wcobq.ankibot.Anki.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserWordDao {
    @NotBlank
    private Long userId;
    @NotBlank
    private String word;
}
