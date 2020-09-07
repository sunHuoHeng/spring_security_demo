package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {

    public User findByUsername(String username);

    public List<Role> findRolesByUsername(String username);
}
