package com.cloud.music.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.entity.ListSong;
import com.cloud.music.entity.Song;
import com.cloud.music.mapper.ListSongMapper;
import com.cloud.music.service.ListSongService;
import com.cloud.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public Map<String, Object> getListSongPages(Page<Song> listSongsPage, ListSong listSong) {

        List<Integer> listSongSIdByCondition = null;
        //进行查询当前歌单关联的歌曲id
        QueryWrapper<Song> listSongWrapper = new QueryWrapper<>(); //构造条件筛选器
        if (!StringUtils.isEmpty(listSong.getId())){  //根据歌单id(主要筛选条件)
            listSongSIdByCondition = this.getListSongsIdByCondition(listSong.getId());
        }

        return songService.getListSongPages(listSongsPage, listSongSIdByCondition);
    }

    @Override
    public List<Integer> getListSongsIdByCondition(String songListId) {
        List<ListSong> song_list_id = this.list(new QueryWrapper<ListSong>().eq("song_list_id", songListId));
        return song_list_id.stream().map(ListSong::getSongId).collect(Collectors.toList());
    }
}
