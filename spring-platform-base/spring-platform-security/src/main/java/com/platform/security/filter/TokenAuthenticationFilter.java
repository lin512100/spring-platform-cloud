package com.platform.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.model.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.platform.common.consts.SecurityConst.*;
import static com.platform.model.entity.user.SysUser.USER_NAME;

/**
 * Token验证过滤器
 * @author lin512100
 * @date 2021/7/19
 */
@EnableAutoConfiguration
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private TokenStore tokenStore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);

        if (StringUtils.isNoneBlank(token)) {
            // 校验Token是否正常
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();

            //取出用户身份信息
            JSONObject json = new JSONObject();
            if (additionalInformation.containsKey(JWT_USER_INFO)) {
                UserInfo userInfo = JSONObject.parseObject(String.valueOf(additionalInformation.get(JWT_USER_INFO)), UserInfo.class);
            }

            //取出用户身份信息
            String principal = String.valueOf(additionalInformation.get(USER_NAME));

            //获取用户的权限
            Object authorities = additionalInformation.get(AUTHORITIES);
            JSONArray jsonArray = json.getJSONArray(String.valueOf(authorities));

            String[] strings = new String[]{};
            if (!CollectionUtils.isEmpty(jsonArray)) {
                strings = jsonArray.toArray(new String[0]);
            }

            //身份信息、权限信息填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null,
                AuthorityUtils.createAuthorityList(strings));
            //创建details
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
