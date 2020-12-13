package com.cloud.music.common.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName UploadToOssService.java
 * @Description
 * @CreateDate 2020-12-13  01:01:42
 */
public interface UploadToOssService{


    /**
     * 上传文件到oss
     * @param file
     * @return
     */
    public String uploadFileAvator(MultipartFile file);

    /**
     * 根据名称删除oss 单个文件
     * @param fileName
     * @return
     */
    public Boolean deleteOneFile(String fileName);

}
