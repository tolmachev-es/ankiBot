package com.wcobq.ankibot.Anki.sender.reverso.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseResults {
    private String translation;
    private List<String> sourceExamples;
    private List<String> targetExamples;
    private boolean rude;
    private boolean colloquial;
    private String partOfSpeech;
    private int frequency;
    private String vowels;
    private String transliteration;
}
