package com.cloud.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.entity.ListSong;
import com.cloud.music.entity.Song;
import com.cloud.music.mapper.ListSongMapper;
import com.cloud.music.service.ListSongService;
import com.cloud.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 歌单包含歌曲列表 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-22
 */
@Service
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {

    @Autowired
    SongService songService;

    @Autowired
    UploadToLocalService uploadToLocalService;

    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;

    @Override
    public Map<String, Object> getListSongPages(Page<Song> listSongsPage, String id) {

        List<Integer> listSongSIdByCondition = null;
        //进行查询当前歌单关联的歌曲id
        if (!id.isEmpty()){  //根据歌单id(主要筛选条件)
            listSongSIdByCondition = this.getListSongsIdByCondition(id);
        }

        return songService.getListSongPages(listSongsPage, listSongSIdByCondition);
    }

    @Override
    public List<Integer> getListSongsIdByCondition(String songListId) {
        List<ListSong> song_list_id = this.list(new QueryWrapper<ListSong>().eq("song_list_id", songListId));
        return song_list_id.stream().map(ListSong::getSongId).collect(Collectors.toList());
    }

    @Override
    public boolean deleteListSongByID(String id,String songId) {
        return this.remove(new QueryWrapper<ListSong>().eq("song_list_id", id).eq("song_id", songId));
    }

    @Override
    public boolean deleteBatchListSongsByIds(String[] params, String id) {
        List<String> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        return this.remove(new QueryWrapper<ListSong>().eq("song_list_id", id).eq("song_id", deleteParams));
    }

    @Override
    public boolean insertlistSongsOne(Song song) {
        //TODO
        return false;
    }

}
