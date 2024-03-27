package org.wcobq.clients;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.wcobq.dao.NewWordDao;
import org.wcobq.dao.User;

import java.util.List;

public class BaseClient {
    protected final RestTemplate restTemplate;

    public BaseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private static ResponseEntity<Object> prepareGatewayResponseAddWord(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.status(response.getStatusCode());
        if (response.hasBody()) {
            return responseBuilder.body(response.getBody());
        }

        return responseBuilder.build();
    }

    private <T> ResponseEntity<Object> makeAndSendAddWordRequest(HttpMethod method, String path, NewWordDao newWord) {
        HttpEntity<?> requestEntity = new HttpEntity<>(newWord, addHttpHeaders(null));
        ResponseEntity<Object> ankiServiceResponse;
        try {
            ankiServiceResponse = restTemplate.exchange(path, method, requestEntity, Object.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponseAddWord(ankiServiceResponse);
    }

    private <T> ResponseEntity<Object> makeAndSendCreateUserRequest(HttpMethod method, String path, User user) {
        HttpEntity<?> requestEntity = new HttpEntity<>(user, addHttpHeaders(null));
        ResponseEntity<Object> createServiceResponse;
        try {
            createServiceResponse = restTemplate.exchange(path, method, requestEntity, Object.class);
        } catch (HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsByteArray());
        }
        return prepareGatewayResponseAddWord(createServiceResponse);
    }

    protected <T> ResponseEntity<Object> postNewWord(String path, NewWordDao newWordDao) {
        return makeAndSendAddWordRequest(HttpMethod.POST, path, newWordDao);
    }

    protected <T> ResponseEntity<Object> postNewUser(String path, User user) {
        return makeAndSendCreateUserRequest(HttpMethod.POST, path, user);
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
