package com.example.userclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserController {

    @Value("${server.port}")
    String port;

    private AtomicInteger atomicInteger =new AtomicInteger();

    @GetMapping("/alive")
//    public String alive(@RequestParam("id") String id) {
    public String alive() {


        System.out.println(port + "===== 第" +  atomicInteger.getAndIncrement() + "调用");
        return "ooxx======"+port;
    }
}
