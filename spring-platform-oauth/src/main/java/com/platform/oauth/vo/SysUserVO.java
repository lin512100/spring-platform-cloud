package com.platform.oauth.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author ChengJianSheng
 * @date 2019-02-12
 */
@Getter
@Setter
@ToString
public class SysUserVO extends User {

    /**
     * 账户渠道类型
     */
    private Integer channel;

    public SysUserVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
