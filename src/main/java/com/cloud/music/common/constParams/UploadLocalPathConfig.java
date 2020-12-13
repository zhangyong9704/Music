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
    public static String singerCoverPath;

    //歌单列表封面地址
    public static String songsCoverPath;

    //上传歌曲保存地址
    public static String songsPath;

    //用户头像上传保存地址
    public static String userCoverPath;

    @Value("${upload.file.singer.path}")
    public  void setSingerCoverPath(String singerCoverPath) {
        UploadLocalPathConfig.singerCoverPath = singerCoverPath;
    }

    @Value("${upload.file.songs.cover.path}")
    public  void setSongsCoverPath(String songsCoverPath) {
        UploadLocalPathConfig.songsCoverPath = songsCoverPath;
    }

    @Value("${upload.file.songs.path}")
    public  void setSongsPath(String songsPath) {
        UploadLocalPathConfig.songsPath = songsPath;
    }

    @Value("${upload.file.user.path}")
    public  void setUserCoverPath(String userCoverPath) {
        UploadLocalPathConfig.userCoverPath = userCoverPath;
    }
}
