package com.wcobq.ankibot.Anki.sender.reverso.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReversoRequest {
    private String format;
    private String from;
    private String to;
    private String input;
    private ReversoOptions options;
}
