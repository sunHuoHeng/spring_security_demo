package com.example.demo.web.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {
    @Resource
    private AuthenticationManager authenticationManager;


    public void login(String username,String password) throws BadCredentialsException,UsernameNotFoundException {
        /**
         * 这个方法内部最后会由org/springframework/security/authentication/mapper/DaoAuthenticationProvider.java 这个类中的additionalAuthenticationChecks方法将获取自己实现的UserDetailService.loadUserByUsername返回的UserDetail（也就是从数据库查询出来的用户和角色信息）中的密码和我们提供的UsernamePasswordAuthenticationToken(username, password)中密码进行匹配,如果匹配正确则认证成功，否则抛出异常。
         */

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
