package com.wcobq.ankibot.Anki.service.interfaces;

import com.wcobq.ankibot.Anki.repository.entities.EngWordEntity;
import com.wcobq.ankibot.Anki.repository.entities.TranslateEntity;

import java.util.List;

public interface TranslateSender {
    List<EngWordEntity> getTranslate(TranslateEntity translateEntity);
}
