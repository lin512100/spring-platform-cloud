package com.platform.oauth.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
public class SysUserVO extends User {


    public SysUserVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
