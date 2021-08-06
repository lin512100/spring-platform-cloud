package com.platform.user.config;

import com.platform.security.filter.TokenAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.HeaderWriterFilter;

/**
 * 资源服务器网络配置
 * @author lin512100
 * @date 7/18/2021
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
            .antMatchers(
                "/**").permitAll()
//                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
                //.antMatchers("/order").hasRole("p1")
                .anyRequest().authenticated();
        http.addFilterAfter(new TokenAuthenticationFilter(), HeaderWriterFilter.class);
    }
}
