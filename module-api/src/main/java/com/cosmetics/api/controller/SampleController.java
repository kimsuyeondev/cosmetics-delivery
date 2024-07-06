package com.cosmetics.api.controller;

import com.cosmetics.domain.sample.dto.Sample;
import com.cosmetics.domain.sample.service.AsyncService;
import com.cosmetics.domain.sample.service.SampleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;
    private AsyncService asyncService;

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

    @GetMapping("asyncTest")
    public void asyncTest() throws InterruptedException {
        for(int i=0; i<1000; i++){
            asyncService.customThread("a",i);
        }

        for(int i=0; i<1000; i++){
            asyncService.customThread("b",i);
        }
    }
}
