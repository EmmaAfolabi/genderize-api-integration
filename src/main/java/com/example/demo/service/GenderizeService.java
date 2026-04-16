package com.example.demo.service;

import com.example.demo.dto.ClassifyData;
import com.example.demo.dto.GenderizeResponse;
import com.example.demo.exception.GenderizeException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;

@Service
public class GenderizeService {

    private final RestTemplate restTemplate;

    public GenderizeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClassifyData classifyName(String name) {
        // Use UriComponentsBuilder to safely encode the name parameter
        String url = UriComponentsBuilder.fromUriString("https://api.genderize.io/")
                .queryParam("name", name)
                .build()
                .toUriString();
        
        GenderizeResponse response;
        try {
            response = restTemplate.getForObject(url, GenderizeResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("External API error");
        }

        // Standard check: if external API returns 200 but gender is null, it means no prediction found
        if (response == null || response.gender() == null) {
            throw new GenderizeException("No prediction available for the name: " + name);
        }

        // High confidence threshold: probability >= 0.9 and significant sample size (>= 1000)
        boolean isConfident = response.probability() != null && 
                             response.probability() >= 0.9 && 
                             response.count() != null && 
                             response.count() >= 1000;

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
