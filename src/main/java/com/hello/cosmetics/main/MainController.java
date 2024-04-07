package com.hello.cosmetics.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String helloCosmetics(){
        return "Hello Cosmetics!";
    }
}
