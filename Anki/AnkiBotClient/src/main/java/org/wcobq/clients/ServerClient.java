package org.wcobq.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;
import org.wcobq.dao.User;

@Service
public class ServerClient extends BaseClient {
    public ServerClient(@Value("${anki.server.port}") String uri, RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(uri))
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory()).build());
    }

    public User createNewUser(User user) {
        return postNewUser("/users", user);
    }

    public NewWordDao addNewWord(NewWordDao newWordDao) {
        return postNewWord("/words", newWordDao);
    }

    public Quiz getQuizFromServer(Long userId) {
        return getQuiz(userId);
    }

    public Boolean patchQuizAnswer(Long userId, String engWord, String answer) {
        return getAnswer(userId, engWord, answer);
    }
}
