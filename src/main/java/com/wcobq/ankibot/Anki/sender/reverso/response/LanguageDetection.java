package com.wcobq.ankibot.Anki.sender.reverso.response;

import lombok.Data;

@Data
public class LanguageDetection {
    private String detectedLanguage;
    private Boolean isDirectionChanged;
    private String originalDirection;
    private int originalDirectionContextMatches;
    private int changedDirectionContextMatches;
    private int timeTaken;
}
