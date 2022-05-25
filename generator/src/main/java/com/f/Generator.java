/*
 * Copyright [2022] [liufeng]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.f;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.f.base.BaseEntity;
import com.f.service.BaseService;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 代码生成
 *
 * @author liuf
 * @date 2021/12/13 18:22
 */
public class Generator {

    /**
     * 根路径(*自己修改*)
     */
    private static final String ROOT_PATH = "D://github/2022/f-cloud/";

    /**
     * 模块(*自己修改*)
     */
    private static final String MODEL = "sell-service";

    /**
     * 数据库(*自己修改*)
     */
    private static final String DB = "f-sell";

    public static void main(String[] args) {
        // 需要生成的表数组
        final String[] tables = {"sell_order", "sell_community"};

        Map<OutputFile, String> pathInfo = getInfo();
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:13306/" + DB + "?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true",
                        "root", "feng")
                .globalConfig(builder -> {
                    builder.author("liuf") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(ROOT_PATH); // 指定输出目录
                })
                .packageConfig(packageInfo(pathInfo))
                .strategyConfig(builder -> builder.addInclude(tables)
                        .entityBuilder().enableLombok()
                        .enableChainModel()
                        .addSuperEntityColumns("id",
                                "create_time",
                                "update_time",
                                "create_id",
                                "update_id",
                                "create_name",
                                "update_name",
                                "is_delete")
                        .superClass(BaseEntity.class)
                        .serviceBuilder().superServiceClass(BaseService.class)
                        .convertServiceFileName((entityName) -> entityName + "Service")
                        .superServiceImplClass("com.f.service.BaseServiceImpl")
                        .mapperBuilder().superClass("com.f.injector.MyBaseMapper")
                        .controllerBuilder().superClass("com.f.controller.BaseController").enableRestStyle())
                .templateConfig(template())
                .injectionConfig(builder -> builder.beforeOutputFile((tab, map) -> tab.getImportPackages().removeIf("java.io.Serializable"::equals))
                        .customFile(customFile())
                        .customMap(customMap()))
                .execute();
        String path = StringUtils.replace(ROOT_PATH, "//", "/") + "service/" + MODEL;
        System.out.println("文件生成成功:" + StringUtils.replace(path, "/", "\\"));
    }

    private static Consumer<PackageConfig.Builder> packageInfo(Map<OutputFile, String> pathInfo) {
        return builder -> {
            builder.parent("com.f")
                    .service("service")
                    .serviceImpl("service.impl")
                    .entity("entity")
                    .mapper("mapper")
                    .controller("controller")
                    .pathInfo(pathInfo); // 设置mapperXml生成路径
        };
    }

    private static Consumer<TemplateConfig.Builder> template() {
        return builder -> builder.entity("templates/entity.java.vm")
                .service("templates/service.java.vm")
                .serviceImpl("templates/serviceImpl.java.vm")
                .controller("templates/controller.java.vm")
                .mapper("templates/mapper.java.vm")
                .mapperXml("templates/mapper.xml.vm");
    }

    /**
     * 自定义文件
     * 文件名->模板名称
     */
    private static Map<String, String> customFile() {
        final HashMap<String, String> map = new HashMap<>(8);
        map.put("index.vue","templates/index.vue.vm");
        map.put("add.vue","templates/add.vue.vm");
        map.put("edit.vue","templates/edit.vue.vm");
        map.put("rule.ts","templates/rule.ts.vm");
        map.put("api.ts","templates/api.ts.vm");
        return map;
    }

    /**
     * 自定义map数据
     */
    private static Map<String, Object> customMap() {
        final HashMap<String, Object> map = new HashMap<>(0);
        map.put("module","sell");
        return map;
    }

    private static Map<OutputFile, String> getInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(8);
        pathInfo.put(OutputFile.mapperXml, ROOT_PATH + "service/" + MODEL + "/src/main/resources/mapper/");
        pathInfo.put(OutputFile.mapper, ROOT_PATH + "service/" + MODEL + "/src/main/java/com/f/mapper/");
        pathInfo.put(OutputFile.entity, ROOT_PATH + "service/" + MODEL + "/src/main/java/com/f/entity/");
        pathInfo.put(OutputFile.service, ROOT_PATH + "service/" + MODEL + "/src/main/java/com/f/service/");
        pathInfo.put(OutputFile.serviceImpl, ROOT_PATH + "service/" + MODEL + "/src/main/java/com/f/service/impl/");
        pathInfo.put(OutputFile.controller, ROOT_PATH + "service/" + MODEL + "/src/main/java/com/f/controller/");
        pathInfo.put(OutputFile.other, ROOT_PATH + "web/");
        return pathInfo;
    }
}
