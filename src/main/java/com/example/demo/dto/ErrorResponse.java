package com.example.demo.dto;

public record ErrorResponse(
    String status,
    String message
) {}
