package com.highio.shoping;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class ReverseGenerator {

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("jianzhang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.highio.shoping") // 设置父包名
                            .entity("pojo")
                            .mapper("mapper")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder ->
                        builder.addInclude("t_seckill_goods").addTablePrefix("t_")
                                .controllerBuilder().enableRestStyle().enableHyphenStyle()
                                .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build()
                )
                .templateConfig(builder -> {
                    builder.entity("templates/entity.java")
                            .mapper("templates/mapper.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .controller("templates/controller.java");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }



}
