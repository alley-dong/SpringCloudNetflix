package com.example.useruseapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/User")
public interface UserUseApi {

    @GetMapping("/alive")
    public String alive();

}
