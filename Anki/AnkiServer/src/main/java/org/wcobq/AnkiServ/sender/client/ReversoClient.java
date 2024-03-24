package org.wcobq.AnkiServ.sender.client;

import com.wcobq.ankibot.Anki.sender.reverso.request.ReversoOptions;
import com.wcobq.ankibot.Anki.sender.reverso.request.ReversoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.wcobq.AnkiServ.sender.client.BaseClient;

@Service
public class ReversoClient extends BaseClient {
    @Autowired
    public ReversoClient(@Value("${reverso.api}") String serverUrl, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory()).build()
        );
    }

    public ResponseEntity<Object> addNewRequest(String text) {
        return post("", createRequest(text));
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
}
