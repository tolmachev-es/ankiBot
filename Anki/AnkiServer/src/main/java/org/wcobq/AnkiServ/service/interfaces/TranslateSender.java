package org.wcobq.AnkiServ.service.interfaces;

import org.wcobq.AnkiServ.repository.entities.EngWordEntity;
import org.wcobq.AnkiServ.repository.entities.TranslateEntity;

import java.util.List;

public interface TranslateSender {
    List<EngWordEntity> getTranslate(TranslateEntity translateEntity);
}
