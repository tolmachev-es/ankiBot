package com.wcobq.ankibot.Anki.sender.reverso.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReversoOptions {
    private boolean sentenceSplitter;
    private String origin;
    private boolean contextResults;
    private boolean languageDetection;
}
