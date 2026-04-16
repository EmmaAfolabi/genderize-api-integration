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

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Validated
public class ClassifyController {

    private final GenderizeService genderizeService;

    public ClassifyController(GenderizeService genderizeService) {
        this.genderizeService = genderizeService;
    }

    @GetMapping(value = {"/classify", "/classify/{name}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClassifyResponse classifyName(
            @RequestParam(value = "name", required = false) String queryName,
            @PathVariable(value = "name", required = false) String pathName) {
        
        String name = queryName != null ? queryName : pathName;
        
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name query parameter is required");
        }
        
        return new ClassifyResponse("success", genderizeService.classifyName(name.trim()));
    }
}
