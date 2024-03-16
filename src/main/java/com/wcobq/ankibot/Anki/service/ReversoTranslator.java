package com.wcobq.ankibot.Anki.service;

import com.wcobq.ankibot.Anki.Reverso.request.ReversoOptions;
import com.wcobq.ankibot.Anki.Reverso.request.ReversoRequest;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateSender;
import org.glassfish.grizzly.http.HttpHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.util.List;
@Service
public class ReversoTranslator implements TranslateSender {
    @Value("${reverso.api}")
    private String api;

    @Override
    public List<String> getTranslate() {
        return null;
    }

    private ReversoRequest createRequest(String text) {
        return ReversoRequest.builder()
                .format("text")
                .from("ru")
                .to("eng")
                .input(text)
                .options(createReversoOprions())
                .build();
    }

    private ReversoOptions createReversoOprions() {
        return ReversoOptions.builder()
                .sentenceSplitter(true)
                .origin("translation.web")
                .contextResults(true)
                .languageDetection(true)
                .build();
    }

    private HttpHeaders addHttpHeaders() {
        HttpHeader httpHeaders = new HttpHeader();
    }
}
