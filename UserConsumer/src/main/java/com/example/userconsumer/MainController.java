package com.example.userconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/alive")
    public String alive(@RequestParam("id") String id){
        return userApi.alive(id);
    }
}
