package com.platform.mybatis.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代碼生成器
 * @author lin512100
 * @date 2021/6/8
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E:\\spring-platform-cloud\\spring-platform-basic");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(false);
        gc.setAuthor("lin512100");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        gc.setServiceName("%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);

        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://42.192.99.152:3306/permission?characterEncoding=utf8&useSSL=true");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        // strategy.setInclude("permission_info", "role_info", "role_permission", "sys_dict", "user_info", "user_role");
        strategy.setInclude("sys_white_route","sys_black_route");
        // 自定义实体父类
        strategy.setSuperEntityClass("com.platform.model.base.BaseEntity");
        strategy.setEntityColumnConstant(true);
        strategy.setEntityLombokModel(true);

        // 自定义实体，公共字段
        strategy.setSuperEntityColumns("id", "creator", "create_time", "updater", "update_time", "valid");
        // 自定义 mapper 父类
        strategy.setSuperMapperClass("com.platform.mybatis.service.BaseMapper");
        // 自定义 service 父类
        strategy.setSuperServiceClass("com.platform.mybatis.service.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.platform.mybatis.service.BaseServiceImpl");
        // 【实体】是否生成字段常量（默认 false）
        strategy.setEntityColumnConstant(true);
        // 生成Rest风格
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.platform.basic");
        // 模块名称
        pc.setModuleName("");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("mapper.impl");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        String dto = "pojo.dto";
        String vo = "pojo.vo";
        // 基础查询类
        String query = "com.platform.model.base.BaseQuery";
        String utils = "com.platform.common.utils";
        String response = "com.platform.common.response";
        String exception = "com.platform.common.exception";
        String annotation = "com.platform.web.annotation";
        String packageWebUtils = "com.platform.mybatis.utils";
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(10);
                map.put("superDtoClass", pc.getParent() + StringPool.DOT + (StringUtils.isBlank(pc.getModuleName()) ? "" : (pc.getModuleName() + StringPool.DOT)) + dto);
                map.put("superDtoQueryClass", query);
                map.put("superVoClass", pc.getParent() + StringPool.DOT + (StringUtils.isBlank(pc.getModuleName()) ? "" : (pc.getModuleName() + StringPool.DOT)) + vo);
                map.put("superVoQueryClass", query);
                map.put("packageUtils", utils);
                map.put("packageResponse", response);
                map.put("packageException", exception);
                map.put("packageAnnotation", annotation);
                map.put("packageWebUtils", packageWebUtils);
                this.setMap(map);
            }
        };

        // 指定文件生成模板
        TemplateConfig tc = new TemplateConfig();
        String templatePath = "/customer/";
        tc.setController(templatePath + "controller.java.vm");
        tc.setEntity(templatePath + "entity.java.vm");
        tc.setMapper(templatePath + "mapper.java.vm");
        tc.setXml(templatePath + "mapper.xml.vm");
        tc.setService(templatePath + "service.java.vm");
        tc.setServiceImpl(templatePath + "serviceImpl.java.vm");
        mpg.setTemplate(tc);

        // 自定义输出配置 自定义配置会被优先输出
        List<FileOutConfig> focList = new ArrayList<>();

        // DTO 文件配置
        String dtoTemplatePath = templatePath + "dto.java.vm";
        focList.add(new FileOutConfig(dtoTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return gc.getOutputDir() + "//" + pc.getParent().replace(".", "//") + pc.getModuleName()
                    + "//" + dto.replace(".", "//") + "//" + tableInfo.getEntityName() + "Dto" + StringPool.DOT_JAVA;
            }
        });
        // VO 文件配置
        String voTemplatePath = templatePath + "vo.java.vm";
        focList.add(new FileOutConfig(voTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return gc.getOutputDir() + "//" + pc.getParent().replace(".", "//") + pc.getModuleName()
                    + "//" + vo.replace(".", "//") + "//" + tableInfo.getEntityName() + "Vo" + StringPool.DOT_JAVA;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 执行生成
        mpg.execute();
    }
}
