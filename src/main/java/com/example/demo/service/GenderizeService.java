package com.example.demo.service;

import com.example.demo.dto.ClassifyData;
import com.example.demo.dto.GenderizeResponse;
import com.example.demo.exception.GenderizeException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.time.Instant;

@Service
public class GenderizeService {

    private final RestTemplate restTemplate;

    public GenderizeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClassifyData classifyName(String name) {
        String url = "https://api.genderize.io/?name=" + name;
        
        GenderizeResponse response;
        try {
            response = restTemplate.getForObject(url, GenderizeResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("External API error");
        }

        if (response == null || response.gender() == null || response.count() == null || response.count() == 0) {
            throw new GenderizeException("No prediction available for the provided name");
        }

        boolean isConfident = response.probability() != null && response.probability() >= 0.7 && response.count() >= 100;

        return new ClassifyData(
            name,
            response.gender(),
            response.probability(),
            response.count(),
            isConfident,
            Instant.now().toString()
        );
    }
}
