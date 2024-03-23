package com.wcobq.ankibot.Anki.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;
import com.wcobq.ankibot.Anki.sender.client.ReversoClient;
import com.wcobq.ankibot.Anki.sender.reverso.response.ResponseResults;
import com.wcobq.ankibot.Anki.sender.reverso.response.ReversoResponse;
import com.wcobq.ankibot.Anki.service.interfaces.TranslateSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReversoTranslator implements TranslateSender {

    private final ReversoClient reversoClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<EngWordEntity> getTranslate(TranslateEntity translateEntity) {
        ResponseEntity<Object> entity = reversoClient.addNewRequest(translateEntity.getRuWord());
        try {
            ReversoResponse reversoResponse = objectMapper.convertValue(entity.getBody(), ReversoResponse.class);
            return getEngWordEntity(reversoResponse, translateEntity);
        } catch (ClassCastException e) {
            return null;
        }
    }

    private List<EngWordEntity> getEngWordEntity(ReversoResponse response, TranslateEntity translateEntity) {
        if (response == null) {
            return null;
        }
        List<EngWordEntity> resultList = new ArrayList<>();
        for (ResponseResults results : response.getContextResults().getResults()) {
            EngWordEntity entity = EngWordEntity.builder()
                    .engWord(results.getTranslation())
                    .build();
            resultList.add(entity);
        }
        return resultList;
    }
}
