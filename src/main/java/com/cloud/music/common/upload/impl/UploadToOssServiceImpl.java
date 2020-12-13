package com.cloud.music.common.upload.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.cloud.music.common.constParams.UploadOssPropertiesConfig;
import com.cloud.music.common.upload.UploadToOssService;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 内容摘要: 上传文件至云服务器
 * <p>文件名称: UploadToOss.java
 * <p>版权所有: 版权所有(C)2015-2020
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @Version v1.0
 * @Author Administrator
 * @Date 2020-12-11 -- 17:39
 * @Description TODO
 */
@Component
public class UploadToOssServiceImpl implements UploadToOssService {


    @Override
    public String uploadFileAvator(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = UploadOssPropertiesConfig.END_POINT;
        String accessKeyId = UploadOssPropertiesConfig.ACCESS_KEY_ID;
        String accessKeySecret = UploadOssPropertiesConfig.ACCESS_KEY_SECRET;
        String bucketName = UploadOssPropertiesConfig.BUCKET_NAME;
        String fileHost = UploadOssPropertiesConfig.FILE_HOST;

        //返回变量值
        String uploadUrl = null;
        try {
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            OSS ossClient =  new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy-MM-dd");
            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            //获取文件类型后缀格式
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            String fileUrl = fileHost + "/" + filePath + "/" + newName;
            //文件上传至阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //获取url地址(需要手动按规则拼接)
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;
        } catch (IOException e) {
            throw new MusicExceptionMessage(20005,"上传文件失败！");
        }
        return uploadUrl;
    }

    @Override
    public Boolean deleteOneFile(String fileName) {

        //获取阿里云存储相关常量
        String endPoint = UploadOssPropertiesConfig.END_POINT;
        String accessKeyId = UploadOssPropertiesConfig.ACCESS_KEY_ID;
        String accessKeySecret = UploadOssPropertiesConfig.ACCESS_KEY_SECRET;
        String bucketName = UploadOssPropertiesConfig.BUCKET_NAME;
        String fileHost = UploadOssPropertiesConfig.FILE_HOST;

        //picture/2020-08-13/1dce60ef-1e85-41f8-96e4-cff80a3c0cec.jpg   服务器文件名
        // 前端传回的格式是： picture_2020-08-13_cebd9966-1184-414e-9d11-93d90dc2e585.jpg
        String newFileName = fileName.replaceAll("_","/");  //将格式进行转换
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, newFileName);
        // 判断文件是否存在。doesObjectExist还有一个参数isOnlyInOSS，如果为true则忽略302重定向或镜像；如果
        //为false，则考虑302重定向或镜像。
        boolean found = ossClient.doesObjectExist(bucketName, newFileName);
        System.out.println(found);
        // 关闭OSSClient。
        ossClient.shutdown();

        return found;

    }


}
