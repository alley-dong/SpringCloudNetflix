package com.dongchanglong.csrf2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    
    @GetMapping("/list")
    public String list(){
        return "list";
    }
}
