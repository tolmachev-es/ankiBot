package com.wcobq.ankibot.Anki.Reverso.response;

import lombok.Data;

import java.util.List;

@Data
public class ContextResponse {
    private boolean rudeWords;
    private boolean collequialisms;
    private boolean riskyWords;
    private List<ResponseResults> results;
    private boolean truncated;
    private int timeTaken;
}
