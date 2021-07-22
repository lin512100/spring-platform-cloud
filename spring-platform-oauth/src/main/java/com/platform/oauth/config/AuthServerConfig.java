package com.platform.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;

import static com.platform.common.consts.SecurityConst.SIGNING_KEY;

/**
 * 授权服务配置
 * @author lin512100
 * @date 2021/7/19
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    @Resource
    private JwtAccessTokenConverter accessTokenConverter;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private DataSource dataSource;

    /**
     *将客户端信息存储到数据库  ,参照JdbcClientDetailsService源码里数据库建表
     */
   private ClientDetailsService clientDetailsService(){
       JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
       clientDetailsService.setPasswordEncoder(passwordEncoder);
       return clientDetailsService;
   }

    /**
     * 配置一个客户端
     * 既可以通过授权码获取令牌，也可以通过密码获取令牌(有四种方式)
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients.withClientDetails(clientDetailsService());
    }

    /**
     * 配置令牌访问端点
     * */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //认证管理器(密码模式所需要的)
                .authenticationManager(authenticationManager)
                //授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                //令牌管理服务
                .tokenServices(tokenServices())
                //允许post提交访问令牌
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 设置授权码模式的授权码如何存取
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 配置token的管理
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //客户端详情服务
        tokenServices.setClientDetailsService(clientDetailsService());
        //支持刷新令牌
        tokenServices.setSupportRefreshToken(true);
        //令牌存储策略
        tokenServices.setTokenStore(tokenStore);

        //令牌增强  使用jwt令牌
        //使用jwt令牌来替代默认令牌，这样做的好处是携带默认令牌访问资源，每次都要通过授权服务来认证令牌是否有效，而jwt则可以做到资源服务中自己解析从而判断令牌的有效性；另外一个优势就是jwt令牌有更高的安全性，可以使用公钥和私钥进行加密和解密，不容易被破解。
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
        tokenServices.setTokenEnhancer(tokenEnhancerChain);

        // 令牌默认有效期2小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }

    /**
     * 默认是InMemoryTokenStore
     * 也可以采用JdbcTokenStore，JwtTokenStore。
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称秘钥，资源服务器使用该秘钥来验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    /**
     * 令牌的访问策略
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                //oauth/token_key是公开
                .tokenKeyAccess("permitAll()")
                //oauth/check_token   已经验证了的客户端才能请求check_token 端点
                .checkTokenAccess("permitAll()")
                //表单认证（申请令牌）
                .allowFormAuthenticationForClients();

    }
}
