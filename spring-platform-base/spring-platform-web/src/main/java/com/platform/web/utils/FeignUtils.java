package com.platform.web.utils;

import com.platform.common.consts.SecurityConst;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 用户Token工具类
 * @author lin512100
 * @date 2021/8/3
 */
public class FeignUtils {

    /**
     * 获取用户自带的Token
     */
    public static String getUserToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if(name.equals(SecurityConst.AUTHORIZATION.toLowerCase())){
                        return request.getHeader(name);
                    }
                }
            }
        }
        throw new RuntimeException("获取用户Token失败");
    }
}
