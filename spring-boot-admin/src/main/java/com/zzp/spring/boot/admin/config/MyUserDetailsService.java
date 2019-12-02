package com.zzp.spring.boot.admin.config;

import com.zzp.spring.boot.admin.constants.Constants;
import com.zzp.spring.boot.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }else if(Constants.NO.equals(user.getIsEnable())){
            throw new UsernameNotFoundException("用户未启用，请联系管理员！");
        }
        var simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        for (var role : user.getRoleList()) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorities);
    }
}
