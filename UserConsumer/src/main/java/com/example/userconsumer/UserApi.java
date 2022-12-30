package com.example.userconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  不结合eureka ，就是自定义一个client名字，通过url属性指定服务器列表 url=“http://ip:port”  没有负载均衡
 */
//@FeignClient(name = "ooxx",url = "http://localhost:80")
@FeignClient(name = "user-provider",fallbackFactory = FallBackFacotryTest.class)
public interface UserApi {

    @GetMapping("/alive")
    public String alive(@RequestParam("id") String id);

}
