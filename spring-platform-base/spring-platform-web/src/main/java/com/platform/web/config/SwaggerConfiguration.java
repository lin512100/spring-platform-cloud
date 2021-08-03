package com.platform.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 * description Swagger-ui 配置
 * @author LinJinTang
 * @date 2020/2/24 14:05
 */
@Configuration
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfiguration implements WebMvcConfigurer{

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket docket() {
        Set<String> set = new HashSet<>();
        set.add("https");
        set.add("http");
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")
            //定义是否开启swagger，false为关闭，可以通过变量控制
            .enable(true)
            // 微信关注开发者技术前线：定义是否开启swagger，false为关闭，可以通过变量控制
            //将api的元信息设置为包含在json ResourceListing响应中。
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.platform"))
            //paths()过滤什么路径
            .paths(PathSelectors.any())
            .build()
            // 支持的通讯协议集合
            .protocols(set)
            // 授权信息设置，必要的header token等认证信息
            .securitySchemes(securitySchemes())
            // 授权信息全局应用
            .securityContexts(securityContexts());
    }
    Contact contact = new Contact("林锦堂","https://github.com/lin512100/spring-platform-cloud","1290364802@qq.com");

    private ApiInfo apiInfo()
    {
        return new ApiInfo("API文档",
            "智能云平台文档",
            "1.0",
            "xiaotangstudio.cn",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());
    }
    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes()
    {
        List<SecurityScheme> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization" ,"Header" );
        result.add(apiKey);
        return  result;
    }
    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
            SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "Authorization")})))
                .build()
        );
    }
}
