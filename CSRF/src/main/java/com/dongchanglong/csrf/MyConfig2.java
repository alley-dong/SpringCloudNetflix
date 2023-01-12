
package com.dongchanglong.csrf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

//@Configuration
//@EnableWebSecurity
public class MyConfig2 extends WebSecurityConfigurerAdapter {
    
    // 这里重写这个configure的方法会 会自动开启csrf的功能，如果不配置csrf 会有一个filter拦截你的请求。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                // 哪些 地址需要登录
                        authorizeRequests()
                //所有请求都需要验证
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .csrf().disable()
                .sessionManagement()
                // 允许同时登录的客户端数量
                .maximumSessions(1)
                // 已有用户登录 不允许相同用户再登录
                .maxSessionsPreventsLogin(true);
    }

    // 所有请求 权限相关的
    // 只要重写了方法 那么在配置文件中的配置就全部都失效
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("dcl").password(new BCryptPasswordEncoder().encode("123")).roles("admin")
                .and()
                .withUser("zl").password(new BCryptPasswordEncoder().encode("123")).roles("user");

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        // 加密
        return new BCryptPasswordEncoder();
        // 不加密
//        return NoOpPasswordEncoder.getInstance();
    }

    public static void main(String[] args) {
        // 
        byte[] string = Base64.getDecoder().decode("ZGNsOjE2NzQ2MTUzMTkyMjk6OTY0M2JlYjc4ZDgzZDY1ODQxOWI5MzQyZWY4M2IxNTI");
        String s = new String(string);
        System.out.println(s);
    }
}
