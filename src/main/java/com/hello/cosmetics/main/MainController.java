package com.hello.cosmetics.main;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public JSONObject helloCosmetics2() {
        JSONObject obj = new JSONObject();
        obj.put("say","Hello");
        return obj;
    }
}
