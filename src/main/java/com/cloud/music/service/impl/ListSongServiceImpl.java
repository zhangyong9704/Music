package com.cloud.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.entity.ListSong;
import com.cloud.music.entity.Song;
import com.cloud.music.entity.vo.ListSongsQueryVo;
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
    public Map<String, Object> getListSongPages(Page<Song> listSongsPage,ListSongsQueryVo listSongsQueryVo) {

        List<String> listSongSIdByCondition = null;
        //进行查询当前歌单关联的歌曲id
        if (null!=listSongsQueryVo){  //根据歌单id(主要筛选条件)
            listSongSIdByCondition = this.getListSongsIdByCondition(listSongsQueryVo.getId()); //返回歌单对应的所有的歌曲信息
        }

        return songService.getListSongPages(listSongsPage, listSongSIdByCondition,listSongsQueryVo);
    }

    @Override
    public List<String> getListSongsIdByCondition(String songListId) {
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
    public boolean insertListSongsOne(Song song,String id) {
        //TODO
        boolean b = songService.insertSongOne(song);   //添加歌曲信息
        String song_id = "";
        if (b){
            song_id = song.getId();  //获得添加歌曲后的主键id
        }
        ListSong listSong = new ListSong();
        listSong.setSongId(song_id);
        listSong.setSongListId(id);
        return this.save(listSong);
    }

}
