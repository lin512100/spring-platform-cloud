package com.platform.oauth.service;

import com.platform.oauth.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 密码登录
 * @author lin512100
 * @date 2021/6/28
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUserVO loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SysUserVO(username, passwordEncoder.encode(username), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
