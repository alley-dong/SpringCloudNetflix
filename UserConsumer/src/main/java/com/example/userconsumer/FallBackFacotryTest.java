package com.example.userconsumer;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FallBackFacotryTest implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable cause) {

        return new UserApi() {
            @Override
            public String alive(String id) {
                // 这个地方 建议自定义异常 然后 通过instanceof 判断
                if (cause instanceof RuntimeException){
                    System.out.println("分母不能为0");
                }
                return "其他异常";
            }
        };
    }
}
