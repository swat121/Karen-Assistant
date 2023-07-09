package com.google.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public abstract class ConnectionService {
    private final RestTemplate restTemplate = new RestTemplate();
    public <T> T getResponseFromService(String name, String url, Class<T> responseType) {
        return restTemplate.getForEntity(URI.create("https://" + name).resolve(url).toString(), responseType).getBody();
    }

    public <T> T getObjectFromService(String name, String url, Class<T> responseType) {
        return restTemplate.getForObject(URI.create("https://" + name).resolve(url).toString(), responseType);
    }
}
