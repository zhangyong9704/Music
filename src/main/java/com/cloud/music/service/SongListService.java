package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.SongList;
import com.cloud.music.entity.vo.SongListQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌单 服务类
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
public interface SongListService extends IService<SongList> {

    /**
     * 方法说明
     * @Title: 获取歌曲分页数据信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:45
     */
    Map<String, Object> getSongListPages(Page<SongList> songListPage, SongListQueryVo songListQueryVo);

    /**
     * 方法说明
     * @Title: 无条件查询所有歌单信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:45
     */
    List<SongList> getAllSongList();

    /**
     * 方法说明
     * @Title: 根据id查询歌单
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:45
     */
    SongList getSongListOneById(Integer id);

    /**
     * 方法说明
     * @Title: 根据id删除歌单
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:45
     */
    boolean deleteSongListByID(String id);

    /**
     * 方法说明
     * @Title: 根据id批量删除歌单
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 17:14
     */
    boolean deleteBatchSongListByIds(String[] params);


    /**
     * 方法说明
     * @Title: 修改SongList信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:45
     */
    boolean updateSongList(SongList songList);

    /**
     * 方法说明
     * @Title: 添加歌单信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 17:16
     */
    boolean insertSongListOne(SongList songList);

    /**
     * 方法说明
     * @Title: 上传歌单封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    String uploadSongListCovers(MultipartFile file);

    /**
     * 方法说明
     * @Title: 删除上次上传歌曲封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    boolean deleteSongListCover(String filePath);
}
