package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClassifyData(
    String name,
    String gender,
    Double probability,
    @JsonProperty("sample_size")
    Integer sampleSize,
    @JsonProperty("is_confident")
    Boolean isConfident,
    @JsonProperty("processed_at")
    String processedAt
) {}
