package com.platform.basic.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件配置属性
 * @author lin512100
 * @date 2021/7/28
 */
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "file")
public class FileProperty {

    /**
     * 文件存储基础路径
     * */
    private String basePath;
}
