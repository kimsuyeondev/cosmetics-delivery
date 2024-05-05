package com.cosmetics.domain.sample.service;

import com.cosmetics.domain.sample.dto.Sample;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public Sample getSample() {
        Sample sample = new Sample();
        sample.setSay("hello cosmetics");
        return sample;
    }
}
