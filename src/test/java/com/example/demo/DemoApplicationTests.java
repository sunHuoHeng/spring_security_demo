package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void contextLoads() {
        List<User> all = mapper.findAll();
        List<Role> roles = all.get(0).getRoles();
        System.out.println(roles.size());
        System.out.println(all);
    }

    @Test
    public void testFind(){
        User admin = mapper.findByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void fingAll(){
        List<Role> all = mapper.findRolesByUsername("admin");

        for (Role role : all) {
            System.out.println("role = " + role);
        }
    }
}
