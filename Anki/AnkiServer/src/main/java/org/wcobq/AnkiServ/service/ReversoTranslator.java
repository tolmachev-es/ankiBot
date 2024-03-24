package org.wcobq.AnkiServ.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.wcobq.AnkiServ.repository.entities.EngWordEntity;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;
import org.wcobq.AnkiServ.sender.client.ReversoClient;
import org.wcobq.AnkiServ.sender.reverso.response.ResponseResults;
import org.wcobq.AnkiServ.sender.reverso.response.ReversoResponse;
import org.wcobq.AnkiServ.service.interfaces.TranslateSender;

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
