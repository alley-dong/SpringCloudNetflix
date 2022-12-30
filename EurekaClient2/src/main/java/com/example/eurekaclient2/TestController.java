package com.example.eurekaclient2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/getHi")
    public String getHi(){
        return "Hi";
    }
}
