package org.wcobq.AnkiServ.service.interfaces;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface AnkiService {
    void getMenu(Update update);

    void addWord(Update update);
}
