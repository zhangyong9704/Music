package com.cloud.music.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 *
 * 继承WebMvcConfigurerAdapter 已经过时
 * 替换方案：
 * ①implements WebMvcConfigurer（官方推荐）
 * ②extends WebMvcConfigurationSupport
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取上传的映射路径
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"uploads"
                        +System.getProperty("file.separator")
                );
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
