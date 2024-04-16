package com.payment.service.util;

import com.payment.service.auth.AuthRequest;
import com.payment.service.auth.AuthResponse;
import com.payment.service.response.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:config.properties")
@RequiredArgsConstructor
public class Util {

    @Value("${api.username}")
    private String apiUsr;

    @Value("${api.password}")
    private String apiPsw;

    private final ObjectMapper objectMapper;

    private String getToken(AuthRequest request, String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        String requestStr = objectMapper.writeValueAsString(request);
        System.out.println("In getToken - " + requestStr);
        ResponseEntity <String> responseStr = restTemplate.postForEntity(url, new HttpEntity<>(requestStr, headers), String.class);
        String responseAsString = responseStr.getBody();
        Response<AuthResponse> response = objectMapper.readValue(responseAsString, new TypeReference<>() {});
        AuthResponse authResponse = response.getT();
        String token = authResponse.getToken();
        System.out.println(token);
        return token;
    }

    public String doGet(String url, String requestUrl, AuthRequest request) throws Exception{
        System.out.println("In util " + url);
        System.out.println("In util " + request.getUserName());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + getToken(request, requestUrl));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return responseEntity.getBody();
    }

    public String doPost(String url, String data, AuthRequest request, String requestUrl) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + getToken(request, requestUrl));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<>(data, headers), String.class);
        String responseStr = objectMapper.writeValueAsString(response);
        System.out.println(responseStr);
        return response.getBody();
    }

    public String doPut(String url, String data, AuthRequest request, String requestUrl) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + getToken(request, requestUrl));
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String responseStr = objectMapper.writeValueAsString(response);
        System.out.println(responseStr);
        return response.getBody();
    }

}
