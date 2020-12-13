package com.cloud.music.common.upload;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName UploadToLocalService.java
 * @Description
 * @CreateDate 2020-12-13  01:14:27
 */
public interface UploadToLocalService {

    /**
     * 方法说明
     * @Title: 单个文件上传到本地
     * @Description TODO filePath:仅songs提供下级文件配置
     * @Param filePath
     * @return
     * @date 2020-12-11 -- 17:34
     */
    public String uploadFileOne(MultipartFile file, String filePath, String uploadType);

    /**
     * 方法说明
     * @Title: 单个文件下载到本地
     * @Description TODO
     * @Param String filePath
     * @return
     * @date 2020-12-11 -- 17:34
     */
    public void downFileToLocal(String filePath, HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * 方法说明
     * @Title: 单个文件下载到本地
     * @Description TODO
     * @Param String fileName,String filePath
     * @return
     * @date 2020-12-11 -- 17:34
     */
    public void downFileToLocal(String fileName,String filePath,HttpServletResponse response) throws UnsupportedEncodingException;

}
