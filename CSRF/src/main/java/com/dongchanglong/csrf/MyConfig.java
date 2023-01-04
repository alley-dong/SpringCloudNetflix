
package com.dongchanglong.csrf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {
    
    // 这里重写这个configure的方法会 会自动开启csrf的功能，如果不配置csrf 会有一个filter拦截你的请求。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http.authorizeRequests()
                //所有请求都需要验证
                .anyRequest().authenticated()
                .and()
                //permitAll 给没登录的 用户可以访问这个地址的权限
                .formLogin().loginPage("/login.html")
                // 登录页面接口跳转
                .loginProcessingUrl("/login")
                // 没登录的用户也有访问的权限
                .permitAll()
                // 失败跳转界面
                .failureUrl("/login.html?error")
                // 成功跳转界面
                .defaultSuccessUrl("/").permitAll()
                .and()
                .csrf()
                // .disable()  禁用csrf
                // 新版本这句话不加也可以  也会下发token
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
        ;
    }
}
