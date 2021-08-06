package com.platform.security.vo;

import com.platform.model.vo.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 认证用户基本信息
 * @author lin512100
 * @date 2021/8/6
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

    public UserInfo getUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setAccName(this.getUsername());

        return userInfo;
    }
}
