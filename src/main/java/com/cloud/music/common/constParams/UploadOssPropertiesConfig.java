package com.cloud.music.common.constParams;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName ConstantPropertiesUtil.java
 * @Description 常量类，读取配置文件application.properties中的配置
 * @CreateDate 2020-08-12  13:36:15
 */
@Component
//@PropertySource("classpath:application.properties")
public class UploadOssPropertiesConfig implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.filehost}")
    private String fileHost;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;


    //用spring的 InitializingBean 的 afterPropertiesSet 来初始化配置信息，这个方法将在所有的属性被初始化后调用。
    //该方式为避免使用get方法获得属性值
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST ;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.END_POINT = endpoint;
        this.ACCESS_KEY_ID = keyId;
        this.ACCESS_KEY_SECRET = keySecret;
        this.BUCKET_NAME = bucketName;
        this.FILE_HOST = fileHost;
    }
}
