package org.wcobq.clients;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;
import org.wcobq.dao.User;

import java.util.List;

public class BaseClient {
    protected final RestTemplate restTemplate;

    public BaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static NewWordDao prepareGatewayResponseAddWord(ResponseEntity<NewWordDao> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }

    private static Quiz prepareGatewayResponseGetQuiz(ResponseEntity<Quiz> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }

    private static User prepareGatewayResponseNewUser(ResponseEntity<User> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException();
        }
    }

    private NewWordDao makeAndSendAddWordRequest(HttpMethod method, String path, NewWordDao newWord) {
        HttpEntity<?> requestEntity = new HttpEntity<>(newWord, addHttpHeaders(null));
        ResponseEntity<NewWordDao> ankiServiceResponse;
        try {
            ankiServiceResponse = restTemplate.exchange(path, method, requestEntity, NewWordDao.class);
        } catch (HttpStatusCodeException e) {
            return null;
        }
        return prepareGatewayResponseAddWord(ankiServiceResponse);
    }

    private User makeAndSendCreateUserRequest(HttpMethod method, String path, User user) {
        HttpEntity<User> requestEntity = new HttpEntity<>(user, addHttpHeaders(null));
        ResponseEntity<User> createServiceResponse;
        try {
            createServiceResponse = restTemplate.postForEntity(path, requestEntity, User.class);
        } catch (HttpStatusCodeException e) {
            throw new RuntimeException();
        }
        return prepareGatewayResponseNewUser(createServiceResponse);
    }

    private Boolean makeAndSendAnswerRequest(String path, Long userId) {
        HttpEntity<?> requestEntity = new HttpEntity<>(addHttpHeaders(String.valueOf(userId)));
        ResponseEntity<Void> response;
        try {
            response = restTemplate.exchange(path, HttpMethod.PATCH, requestEntity, Void.class);
        } catch (HttpStatusCodeException e) {
            return false;
        }
        return response.getStatusCode().is2xxSuccessful();
    }

    private Quiz makeAndSendGetQuizRequest(HttpMethod method, String path, Long userId) {
        HttpEntity<?> requestEntity = new HttpEntity<>(addHttpHeaders(String.valueOf(userId)));
        ResponseEntity<Quiz> response;
        try {
            response = restTemplate.exchange(path, HttpMethod.GET, requestEntity, Quiz.class);
        } catch (HttpStatusCodeException e) {
            throw new RuntimeException();
        }
        return prepareGatewayResponseGetQuiz(response);
    }

    protected NewWordDao postNewWord(String path, NewWordDao newWordDao) {
        return makeAndSendAddWordRequest(HttpMethod.POST, path, newWordDao);
    }

    protected User postNewUser(String path, User user) {
        return makeAndSendCreateUserRequest(HttpMethod.POST, path, user);
    }

    protected Quiz getQuiz(Long userId) {
        return makeAndSendGetQuizRequest(HttpMethod.GET, "/quiz", userId);
    }

    protected Boolean getAnswer(Long userId, String engWord, String answer) {
        return makeAndSendAnswerRequest("/quiz/" + answer + "/answer/" + engWord, userId);
    }

    private HttpHeaders addHttpHeaders(@Nullable String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (userId != null) {
            httpHeaders.add("X-User-Id", userId);
        }
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

}
