package com.dongchanglong.csrf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @GetMapping("/hi")
    public String hi(){
        System.out.println("hi ~  ~");
        return "hi";
    }
}
