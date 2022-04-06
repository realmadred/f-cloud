/*
 * Copyright [2021] [liufeng]
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
import com.f.service.BaseServiceImpl;
import com.f.service.base.BaseService;

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

    public static void main(String[] args) {
        final String rootPath = "D://github/2021/f-boot";
        final String model = "sys";
        Map<OutputFile, String> pathInfo = getInfo(rootPath, model);
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:13306/f-boot?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&characterEncoding=utf8",
                        "root", "feng")
                .globalConfig(builder -> {
                    builder.author("liuf") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(rootPath); // 指定输出目录
                })
                .packageConfig(packageInfo(model, pathInfo))
                .strategyConfig(builder -> builder.addInclude("sys_dict", "sys_dict_detail")
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
                        .superServiceImplClass(BaseServiceImpl.class)
                        .mapperBuilder().superClass("com.f.mapper.injector.MyBaseMapper")
                        .controllerBuilder().superClass("com.f.controller.BaseController").enableRestStyle())
                .templateConfig(template())
                .injectionConfig(builder -> builder.beforeOutputFile((tab, map) -> tab.getImportPackages().removeIf("java.io.Serializable"::equals)))
                .execute();
    }

    private static Consumer<PackageConfig.Builder> packageInfo(String model, Map<OutputFile, String> pathInfo) {
        return builder -> {
            final String service = "service.";
            builder.parent("com.f")
                    .service(service + model)
                    .serviceImpl(service + model)
                    .entity("entity." + model)
                    .mapper("mapper." + model)
                    .controller("controller." + model)
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

    private static Map<OutputFile, String> getInfo(String rootPath, String model) {
        Map<OutputFile, String> pathInfo = new HashMap<>(8);
        pathInfo.put(OutputFile.mapperXml, rootPath + "/service/src/main/resources/mapper/" + model);
        pathInfo.put(OutputFile.mapper, rootPath + "/service/src/main/java/com/f/mapper/" + model);
        pathInfo.put(OutputFile.entity, rootPath + "/api/src/main/java/com/f/entity/" + model);
        pathInfo.put(OutputFile.service, rootPath + "/api/src/main/java/com/f/service/" + model);
        pathInfo.put(OutputFile.serviceImpl, rootPath + "/service/src/main/java/com/f/service/" + model);
        pathInfo.put(OutputFile.controller, rootPath + "/web/src/main/java/com/f/controller/" + model);
        return pathInfo;
    }
}
