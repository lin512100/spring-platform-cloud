package com.platform.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.platform.security.consts.SecurityConst.*;


/**
 * Token验证过滤器
 * @author lin512100
 * @date 2021/7/19
 */
@Configuration
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(ACCESS_TOKEN);
        if(StringUtils.isNotBlank(token)){
            JSONObject json = JSON.parseObject(token);
            //获取用户的身份信息，权限信息
            String principal = json.getString(PRINCIPAL);
            JSONArray jsonArray = json.getJSONArray(AUTHORITIES);
            String[] strings = new String[]{};
            if(!CollectionUtils.isEmpty(jsonArray)){
                strings = jsonArray.toArray(new String[0]);
            }
            //身份信息、权限信息填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(principal,null,
                    AuthorityUtils.createAuthorityList(strings));
            //创建details
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request,response);
    }
}
