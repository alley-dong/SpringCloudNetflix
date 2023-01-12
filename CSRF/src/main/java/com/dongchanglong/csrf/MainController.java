package com.dongchanglong.csrf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Queue;
import java.util.concurrent.*;

@Controller
public class MainController {
    
    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/ok.html")
    public String ok(){
        return "ok";
    }
    
    
    
}
