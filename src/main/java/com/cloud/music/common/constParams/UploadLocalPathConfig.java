package com.cloud.music.common.constParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName UploadLocalPathConfig.java
 * @Description 本地文件路径上传地址配置
 * @CreateDate 2020-12-13  13:04:35
 */
@Component
public class UploadLocalPathConfig {

    //歌手封面图片地址
    @Value("${upload.file.singer.path}")
    private String singerCoverPath;

    //歌单列表封面地址
    @Value("${upload.file.songs.cover.path}")
    private String songsCoverPath;

    //上传歌曲保存地址
    @Value("${upload.file.songs.path}")
    private String songsPath;

    //用户头像上传保存地址
    @Value("${upload.file.user.path}")
    private String userCoverPath;


}
