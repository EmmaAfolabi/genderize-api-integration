package com.example.demo.controller;

import com.example.demo.dto.ClassifyResponse;
import com.example.demo.service.GenderizeService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Validated
public class ClassifyController {

    private final GenderizeService genderizeService;

    public ClassifyController(GenderizeService genderizeService) {
        this.genderizeService = genderizeService;
    }

    @GetMapping("/classify")
    public ClassifyResponse classifyName(@RequestParam("name") @NotBlank(message = "Name cannot be empty") String name) {
        return new ClassifyResponse("success", genderizeService.classifyName(name));
    }
}
