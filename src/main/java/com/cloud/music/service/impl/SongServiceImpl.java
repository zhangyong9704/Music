package com.cloud.music.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.entity.Song;
import com.cloud.music.entity.vo.SongQueryVo;
import com.cloud.music.mapper.SongMapper;
import com.cloud.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 歌曲 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

    @Autowired
    UploadToLocalService uploadToLocalService;

    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;

    @Override
    public Map<String, Object> getSongPages(Page<Song> songsPage, SongQueryVo songQueryVo) {

        QueryWrapper<Song> songWrapper = new QueryWrapper<>(); //构造条件筛选器
        if (!StringUtils.isEmpty(songQueryVo.getName())){   //根据歌名查询
            songWrapper.like("name",songQueryVo.getName());
        }
        if (null!=songQueryVo.getLyric()){ //歌词模糊匹配
            songWrapper.like("lyric",songQueryVo.getLyric());
        }
        if (!StringUtils.isEmpty(songQueryVo.getIntroduction())){  //根据专辑
            songWrapper.like("introduction",songQueryVo.getIntroduction());
        }
        if (!StringUtils.isEmpty(songQueryVo.getSingerId())){  //根据歌手id(主要筛选条件)
            songWrapper.eq("singer_id",songQueryVo.getSingerId());
        }
        songWrapper.orderByAsc("id");//根据id排序

        Page<Song> page = this.page(songsPage, songWrapper);

        Map<String,Object> result = new HashMap<>();
        result.put("Songs",page.getRecords()); //获取歌曲集合
        result.put("total", page.getTotal());  //获取记录总数

        return result;
    }

    @Override
    public List<Song> getAllSongs() {
        return this.list();
    }

    @Override
    public boolean deleteSongByID(String id) {
        Song songs = this.getById(id);
        boolean deleteCover = uploadToLocalService.deleteFile(songs.getPic()); //获得图片地址并删除
        boolean deleteFile = uploadToLocalService.deleteFile(songs.getUrl()); //获得文件地址并删除
        return deleteCover && deleteFile && this.removeById(id);
    }

    @Override
    public boolean deleteBatchSongsByIds(String[] params) {
        List<String> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        //获取Songs集合
        List<Song> songsCollect = baseMapper.selectBatchIds(deleteParams);
        List<String> coverCollect = songsCollect.stream().map(Song::getPic).collect(Collectors.toList());
        List<String> filesCollect = songsCollect.stream().map(Song::getUrl).collect(Collectors.toList());
        boolean batchCovers = uploadToLocalService.deleteBatchFiles(coverCollect);  //批量删除图片
        boolean batchFiles = uploadToLocalService.deleteBatchFiles(filesCollect);  //批量删除文件
        return batchCovers && batchFiles && this.removeByIds(deleteParams);
    }

    @Override
    public boolean updateSongs(Song songs) {
        return this.updateById(songs);
    }

    @Override
    public boolean insertSongOne(Song song) {
        boolean save =false;
        if (null != song){
            save = this.save(song);
        }
        return save;
    }

    @Override
    public String uploadSongsCovers(MultipartFile file) {
        String songsCoverPath = uploadToLocalService.uploadFileOne(file,
                UploadLocalPathConfig.songsCoverPath, "SONGS");
        return null==songsCoverPath?"":songsCoverPath;
    }


    @Override
    public String uploadSongsFile(MultipartFile file) {
        String songsFiles = uploadToLocalService.uploadFileOne(file, UploadLocalPathConfig.songsPath, "SONGS");
        return null==songsFiles?"":songsFiles;
    }

    @Override
    public boolean deleteSongsCoverAndFiles(String filePath) {
        return uploadToLocalService.deleteFile(filePath);
    }

    @Override
    public Song getSongOneById(Integer id) {
        return this.getById(id);
    }


    @Override
    public Map<String, Object> getListSongPages(Page<Song> listSongsPage, List<Integer> listSongSId) {
        //进行查询当前歌单关联的歌曲id
        QueryWrapper<Song> listSongWrapper = new QueryWrapper<>(); //构造条件筛选器
        listSongWrapper.in("id",listSongSId);
        listSongWrapper.orderByAsc("id");//根据id排序

        Page<Song> page = this.page(listSongsPage, listSongWrapper);

        Map<String,Object> result = new HashMap<>();
        result.put("listSongs",page.getRecords()); //获取歌单下歌曲集合
        result.put("total", page.getTotal());  //获取记录总数

        return result;
    }
}
