package com.wcobq.ankibot.Anki.Reverso.response;

import lombok.Data;

import java.util.List;

@Data
public class ReversoResponse {
    private String id;
    private String from;
    private String to;
    private List<String> input;
    private boolean correctedText;
    private List<String> translation;
    private List<String> engines;
    private LanguageDetection languageDetection;
    private List<ContextResponse> contextResults;
}
