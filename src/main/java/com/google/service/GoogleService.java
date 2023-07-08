package com.google.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GoogleService {

    public String parametersHandler(Map<String, Object> parameters) {
        return "Done!";
    }
}
