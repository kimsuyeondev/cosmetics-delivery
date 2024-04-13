package com.cosmetics.sample.service;

import com.cosmetics.Sample;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public Sample getSample() {
        Sample sample = new Sample();
        sample.setSay("hello cosmetics");
        return sample;
    }
}
