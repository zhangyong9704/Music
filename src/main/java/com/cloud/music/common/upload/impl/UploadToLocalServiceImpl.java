package com.cloud.music.common.upload.impl;

import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.cloud.music.utils.ReturnStatusCode.ERROR_STATUS;

/**
 * 内容摘要:文件上传到本地
 * <p>文件名称: uploadPicture.java
 * <p>版权所有: 版权所有(C)2015-2020
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @Version v1.0
 * @Author Administrator
 * @Date 2020-12-11 -- 17:33
 * @Description TODO
 */
@Component
public class UploadToLocalServiceImpl implements UploadToLocalService {

    /**
     * 方法说明
     * @Title: 单个文件上传到本地
     * @Description TODO filePath:仅songs提供下级文件配置
     * @Param filePath
     * @return
     * @date 2020-12-11 -- 17:34
    */
    public String uploadFileOne(MultipartFile file,String filePath,String uploadType) {
        if(file.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传文件为空");
        }
        if (filePath.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传文件路径为空");
        }
        if (StringUtils.isEmpty(uploadType)){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传文件类型为空");
        }
        String storePath = ""; //最终存储路径
        // 使用日期来分类管理上传的文件
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if ("SINGER".equalsIgnoreCase(uploadType)){ //当前类型是歌手图片上传
            storePath = System.getProperty("user.dir")+System.getProperty("file.separator")+filePath
                    +System.getProperty("file.separator")+format;
        }
        if ("SONGS".equalsIgnoreCase(uploadType)){  //歌曲存储路径
            storePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"songs"
                    +System.getProperty("file.separator")+filePath+System.getProperty("file.separator")+format;
        }
        if ("USERS".equalsIgnoreCase(uploadType)){  //用户图片存储路径
            storePath = System.getProperty("user.dir")+System.getProperty("file.separator")+filePath
                    +System.getProperty("file.separator")+format;
        }
        File folder = new File(storePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        String newName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        File newFile = new File(folder, newName);
        try {
            //保存文件，返回文件路径
            file.transferTo(newFile);
            return folder +System.getProperty("file.separator") + newName;
        } catch (IOException ioException) {
            throw new MusicExceptionMessage(ERROR_STATUS,ioException.getMessage());
        }
    }



    /**
     * 方法说明
     * @Title: 单个文件下载到本地
     * @Description TODO
     * @Param String filePath
     * @return
     * @date 2020-12-11 -- 17:34
     */
    public void downFileToLocal(String filePath, HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(filePath);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filePath,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                throw new MusicExceptionMessage(ERROR_STATUS,e.getMessage());
            }
            System.out.println("------File Downloading---:" + filePath);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                throw new MusicExceptionMessage(ERROR_STATUS,e.getMessage());
            }
        }
    }


    /**
     * 方法说明
     * @Title: 单个文件下载到本地
     * @Description TODO
     * @Param String fileName,String filePath
     * @return
     * @date 2020-12-11 -- 17:34
     */
    public void downFileToLocal(String fileName,String filePath,HttpServletResponse response) throws UnsupportedEncodingException {
//        String filename="2.xlsx";
//        String filePath = "D:/download" ;
        String FilePath = filePath + "/" + fileName;
        this.downFileToLocal(FilePath,response);
    }




}
