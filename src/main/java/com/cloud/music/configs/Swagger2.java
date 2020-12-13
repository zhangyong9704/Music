package com.cloud.music.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 内容摘要:
 * <p>文件名称: Swagger2.java
 * <p>版权所有: 版权所有(C)2015-2020
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @Version v1.0
 * @Author zy
 * @Date 2020-12-03 -- 15:08
 * @Description TODO
 */
@Configuration
@EnableOpenApi
//@EnableSwagger2
public class Swagger2 {
    @Bean // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.cloud.music.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("音乐网站后台系统——API文档") // 页面标题
                .description("本文档描述提供音乐网站后台接口定义")  // 描述
                .version("1.0")  // 版本号
                .contact(new Contact("waitforyou2113", "https://github.com/zhangyong9704/music-manage", "waitforyou2113@qq.com")) // 创建人信息
                .build();
    }
}
