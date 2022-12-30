package com.example.userconsumer;

import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FallBackTest implements UserApi{
        @Override
        public String alive(String id) {
            return "降级了";
        }
}
