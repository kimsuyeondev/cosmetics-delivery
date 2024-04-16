package com.cosmetics.sample.controller;

import com.cosmetics.Sample;
import com.cosmetics.sample.service.SampleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;
    @GetMapping("/hello")
    public Sample getHelloCosmetics() {
        Sample say = sampleService.getSample();
        return say;
    }
    @PostMapping("/hello")
    public Sample postHelloCosmetics(@RequestBody Sample sampleParam) {
        return sampleParam;
    }

    @GetMapping("/helloObj")
    public String getHelloObjectMapper() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString("{\"say\": \"hello\" }");
        return str;
    }
}
