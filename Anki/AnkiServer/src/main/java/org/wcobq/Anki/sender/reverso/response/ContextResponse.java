package com.wcobq.ankibot.Anki.sender.reverso.response;

import lombok.Data;

import java.util.List;

@Data
public class ContextResponse {
    private Boolean rudeWords;
    private Boolean collequialisms;
    private Boolean riskyWords;
    private List<ResponseResults> results;
    private Boolean truncated;
    private int timeTaken;
}
