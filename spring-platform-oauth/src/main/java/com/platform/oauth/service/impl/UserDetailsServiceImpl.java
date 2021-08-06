package com.platform.oauth.service.impl;

import com.platform.model.vo.OauthUserVo;
import com.platform.openfeign.service.UserApiService;
import com.platform.openfeign.utils.FeignUtils;
import com.platform.security.vo.SysUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 密码登录
 * @author lin512100
 * @date 2021/6/28
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserApiService userApiService;

    @Override
    public SysUserVO loadUserByUsername(String username) throws UsernameNotFoundException {
        OauthUserVo oauthUserVo = userApiService.loadUserByUsername(username, FeignUtils.getInnerToken());
        List<SimpleGrantedAuthority> authorities = oauthUserVo.getGrantedAuthorityList().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        SysUserVO userVO =  new SysUserVO(username, oauthUserVo.getPassword(), authorities);
        userVO.setChannel(oauthUserVo.getChannel());
        return userVO;
    }
}
