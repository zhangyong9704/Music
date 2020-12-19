package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.Song;
import com.cloud.music.entity.vo.SongQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌曲 服务类
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
public interface SongService extends IService<Song> {

    /**
     * 方法说明
     * @Title: 获取歌曲分页数据信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 14:45
     */
    Map<String, Object> getSongPages(Page<Song> songsPage, SongQueryVo songQueryVo);

    /**
     * 方法说明
     * @Title: 查询歌曲全部信息(没有条件)
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    List<Song> getAllSongs();

    /**
     * 方法说明
     * @Title: 根据歌曲id删除
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    boolean deleteSongByID(String id);

    /**
     * 方法说明
     * @Title: 根据歌曲id批量删除
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    boolean deleteBatchSongsByIds(String[] params);

    /**
     * 方法说明
     * @Title: 根据歌曲修改
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    boolean updateSongs(Song songs);

    /**
     * 方法说明
     * @Title: 添加歌曲信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    boolean insertSongOne(Song song);

    /**
     * 方法说明
     * @Title: 上传歌单封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    String uploadSongsFileOne(MultipartFile file);

    /**
     * 方法说明
     * @Title: 删除上一次歌单封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    boolean deletePreviousSongsCover(String filePath);

    /**
     * 方法说明
     * @Title: 根据id 获取songs
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:45
     */
    Song getSongOneById(Integer id);
}
