
package com.dongchanglong.csrf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                //自定义登录界面login.html
                //permitAll 给没登录的 用户可以访问这个地址的权限
                .formLogin().loginPage("/login.html").permitAll()
                // 自定义表单
//                .usernameParameter("oo")
//                .passwordParameter("xx")
                // 登录页面接口跳转
                .loginProcessingUrl("/login")
                // 没登录的用户也有访问的权限
                .permitAll()
                // 失败跳转界面
                .failureUrl("/login.html?error")
                // 
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        exception.printStackTrace();
                        //判断异常信息 跳转不同页面
                        request.getRequestDispatcher(request.getRequestURL().toString()).forward(request,response);
                        
                        //可以记录登录失败的次数 等等  所有登陆失败的需求都可以在这里完成
                    }
                })
                // 成功跳转界面
                .defaultSuccessUrl("/",true).permitAll()
                .and()
                .csrf()
                // .disable()  禁用csrf
                // 新版本这句话不加也可以  也会下发token
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
        ;
    }

    
    // 所有请求 权限相关的
    // 只要重写了方法 那么在配置文件中的配置就全部都失效
    // 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("dcl").password("123").roles("admin")
                .and()
                .withUser("zl").password("123").roles("user");
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
