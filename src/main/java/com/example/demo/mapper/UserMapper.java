package com.example.demo.mapper;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

import java.util.List;

public interface UserMapper {

    public List<User> findAll();

    /**
     *
     * @param username 用户名称
     * @return
     */
    public User findByUsername(String username);


    public List<Role> findRolesByUsername(String username);
}
