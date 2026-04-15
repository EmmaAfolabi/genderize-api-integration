package com.example.demo.dto;

public record GenderizeResponse(
    String name,
    String gender,
    Double probability,
    Integer count
) {}
