package com.cloud.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>文件名称: MusicApplication.java
 * @author ZhangYong
 * @version v1.0
 * @date 2020-10-26  14:27
 */
@SpringBootApplication
@MapperScan(basePackages = "com.cloud.music.dao") //需要给出mapper扫描位置，不然会报错无法找到对应配置
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

}
