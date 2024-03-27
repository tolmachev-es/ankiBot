package org.wcobq.AnkiServ.service.interfaces;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.wcobq.dao.NewWordDao;

@Service
public interface AnkiService {
    void getMenu(Update update);

    NewWordDao addWord(NewWordDao newWordDao);
}
