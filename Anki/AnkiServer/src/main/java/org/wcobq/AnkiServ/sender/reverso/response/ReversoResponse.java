package org.wcobq.AnkiServ.sender.reverso.response;

import lombok.Data;

import java.util.List;

@Data
public class ReversoResponse {
    private String id;
    private String from;
    private String to;
    private List<String> input;
    private Boolean correctedText;
    private List<String> translation;
    private List<String> engines;
    private LanguageDetection languageDetection;
    private ContextResponse contextResults;
    private Boolean truncated;
    private int timeTaken;
}
