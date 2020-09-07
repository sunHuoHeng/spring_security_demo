package com.example.demo.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public String hello(){
        return "hello";
    }

}
