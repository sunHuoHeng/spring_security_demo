package com.example.demo.web.security.service;

import com.example.demo.entity.Role;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.example.demo.entity.User user1 = userService.findByUsername(s);
        if(user1 == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        User user = new User(user1.getUsername(),user1.getPassword(),user1.getEnable(),user1.getAccountNonExpired(),user1.getCredentialsNonExpired(),user1.getAccountNonLocked(),getGrantedAuthority(s));
        return user;
    }

    private Collection<GrantedAuthority> getGrantedAuthority(String s){
        Collection<GrantedAuthority> collection = new ArrayList<>();
        List<Role> rolesByUsername = userService.findRolesByUsername(s);
        for (Role role : rolesByUsername) {
            collection.add(new SimpleGrantedAuthority(role.getRoleKey()));
        }
        return collection;
    }
}
