package org.wcobq.clients;

import org.glassfish.jersey.server.Uri;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.Quiz;
import org.wcobq.dao.User;

import java.net.URI;
import java.util.List;

public class BaseClient {
    protected final RestTemplate restTemplate;

    public BaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private static ResponseEntity<NewWordDao> prepareGatewayResponseAddWord(ResponseEntity<NewWordDao> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());
        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }

        return responseBuilder.build();
    }

    private <T> ResponseEntity<NewWordDao> makeAndSendAddWordRequest(HttpMethod method, String path, NewWordDao newWord) {
        HttpEntity<?> requestEntity = new HttpEntity<>(newWord, addHttpHeaders(null));
        ResponseEntity<NewWordDao> ankiServiceResponse;
        try {
            ankiServiceResponse = restTemplate.exchange(path, method, requestEntity, NewWordDao.class);
        } catch (HttpStatusCodeException e) {
            return null;
        }
        return prepareGatewayResponseAddWord(ankiServiceResponse);
    }

    private <T> ResponseEntity<Object> makeAndSendCreateUserRequest(HttpMethod method, String path, User user) {
        HttpEntity<?> requestEntity = new HttpEntity<>(user, addHttpHeaders(null));
        ResponseEntity<NewWordDao> createServiceResponse;
        try {
            createServiceResponse = restTemplate.exchange(path, method, requestEntity, Object.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponseAddWord(createServiceResponse);
    }

    private <T> Quiz makeAndSendGetQuizRequest(HttpMethod method, String path, Long userId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path + userId);
        URI uri = builder.build(false).toUri();
        return restTemplate.getForObject(uri, Quiz.class);
    }

    protected <T> ResponseEntity<NewWordDao> postNewWord(String path, NewWordDao newWordDao) {
        return makeAndSendAddWordRequest(HttpMethod.POST, path, newWordDao);
    }

    protected <T> ResponseEntity<Object> postNewUser(String path, User user) {
        return makeAndSendCreateUserRequest(HttpMethod.POST, path, user);
    }

    protected <T> Quiz getQuiz(Long userId) {
        return makeAndSendGetQuizRequest(HttpMethod.GET, "/quiz", userId);
    }

    private HttpHeaders addHttpHeaders(@Nullable String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if(userId != null) {
            httpHeaders.add("X-UserId", userId);
        }
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

}
