package com.platform.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

/**
 * 资源服务配置
 * @author lin512100
 * @date 7/18/2021
 */
@Configuration
@EnableResourceServer
public class ResourceConfigServer extends ResourceServerConfigurerAdapter {
    /**
     * 密钥
     */
    private static final String SIGNING_KEY = "oauth";

    public static final String RESOURCE_ID = "basic";

    @Resource
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
            //.tokenServices(tokenService())   //ResourceServerTokenServices 类的实例，用来实现令牌服务
            .tokenStore(tokenStore)
            .stateless(true);
    }

    /**
     * 默认是InMemoryTokenStore
     * 也可以采用JdbcTokenStore，JwtTokenStore。
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称秘钥，资源服务器使用该秘钥来验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(
                "/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/**",
                "/actuator/**", "/oauth/authorize", "/oauth/**").permitAll()
            .antMatchers("/**")
            .access("#oauth2.hasScope('ROLE_ADMIN')")
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
