package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.ListSong;
import com.cloud.music.entity.Song;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌单包含歌曲列表 服务类
 * </p>
 *
 * @author zy
 * @since 2020-12-22
 */
public interface ListSongService extends IService<ListSong> {

    /**
     * 方法说明
     * @Title: 带条件的的歌单下所有歌曲信息分页查询
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:37
     */
    Map<String, Object> getListSongPages(Page<Song> listSongsPage, String id);

    /**
     * 方法说明
     * @Title: 根据歌单id查询包含的song_ids
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 14:37
     */
    List<Integer> getListSongsIdByCondition(String songListId);



    /**
     * 方法说明
     * @Title: 根据ID 删除歌单下的歌曲
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:13
     */
    boolean deleteListSongByID(String id,String songId);

    /**
     * 方法说明
     * @Title: 根据id批量删除歌曲
     * @Description TODO
     * @Param id 歌单id
     * @return
     * @date 2020-12-11 -- 17:14
     */
    boolean deleteBatchListSongsByIds(String[] params, String id);

    /**
     * 方法说明
     * @Title: 添加歌单的歌曲信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    boolean insertlistSongsOne(Song song);


}
