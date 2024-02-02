package com.blog.springbootvue;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/shiro_test_db?useUnicode=true&characterEncoding=utf-8", "root", "123456")
            .globalConfig(builder -> {
                builder.author("motong") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .commentDate("yyyy-MM-dd hh:mm:ss")
                    .outputDir(System.getProperty("user.dir") + "/src/main/java") // 指定输出目录
                    .disableOpenDir(); //禁止打开输出目录，默认打开
            })
            .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                if (typeCode == Types.SMALLINT) {
                    // 自定义类型转换
                    return DbColumnType.INTEGER;
                }
                return typeRegistry.getColumnType(metaInfo);

            }))
            .packageConfig(builder -> {
                builder.parent("com.blog") // 设置父包名
                    .moduleName("springbootvue") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.xml,
                             System.getProperty("user.dir") + "/src/main/resources/mapper")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("t_role_permission") // 设置需要生成的表名
                    .addTablePrefix("t_", "c_") // 设置过滤表前缀
                    // Entity 策略配置
                    .entityBuilder()
                    .enableLombok() //开启 Lombok
                    .enableFileOverride() // 覆盖已生成文件
                    .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                    .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                    // Mapper 策略配置
                    .mapperBuilder()
                    .enableFileOverride() // 覆盖已生成文件
                    // Service 策略配置
                    .serviceBuilder()
                    .enableFileOverride() // 覆盖已生成文件
                    .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                    .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                    // Controller 策略配置
                    .controllerBuilder()
                    .enableFileOverride(); // 覆盖已生成文件
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }

}
