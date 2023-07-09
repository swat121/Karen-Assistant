package com.google.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleService extends ConnectionService {

    public String parametersHandler(Map<String, Object> parameters) {
        return "Done!";
    }
}
