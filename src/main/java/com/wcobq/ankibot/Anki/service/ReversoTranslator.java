package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.service.interfaces.TranslateSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReversoTranslator implements TranslateSender {
    @Value("${reverso.api}")
    private String api;

    @Override
    public List<String> getTranslate() {
        return null;
    }


}
