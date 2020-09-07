package com.example.demo.web.controller;

import com.example.demo.utils.Base64;
import com.example.demo.utils.RedisCache;
import com.example.demo.utils.VerifyCodeUtils;
import com.example.demo.web.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisCache redisCache;
    @GetMapping
    public String getLogin(){
        return "login";
    }

    @GetMapping("image")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String s = VerifyCodeUtils.generateVerifyCode(4);
        redisCache.setCacheObject("user",s,3,TimeUnit.MINUTES);
        response.setContentType("image/jpeg");
        ByteArrayOutputStream os = new ByteArrayOutputStream();;
        try {
            VerifyCodeUtils.outputImage(100,30,os,s);
            ServletOutputStream stream = response.getOutputStream();
            stream.write(os.toByteArray());
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    @PostMapping
    public String login(HttpServletRequest request, String username, String password, String code){
        Object user = redisCache.getCacheObject("user");
        System.out.println("user = " + user);
        System.out.println("code = " + code);
        redisCache.deleteObject("user");
        if (user == null){
            request.setAttribute("error","验证码已过期");
            return "login";
        }
        if (!code.toLowerCase().equals(user.toString().toLowerCase())) {
            request.setAttribute("error","验证码错误");
            return "login";
        } else {
            try {
                loginService.login(username, password);
            } catch (BadCredentialsException e){
                request.setAttribute("error","密码错误");
                System.out.println("密码不对");
                return "login";
            } catch (UsernameNotFoundException e){
                request.setAttribute("error","用户不存在！");
                System.out.println("用户不存在");
                return "login";
            }
        }
        return "hello";
    }
}
