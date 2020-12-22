package com.cloud.music.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.entity.SongList;
import com.cloud.music.entity.vo.SongListQueryVo;
import com.cloud.music.mapper.SongListMapper;
import com.cloud.music.service.SongListService;
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
 * 歌单 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Autowired
    UploadToLocalService uploadToLocalService;

    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;

    @Override
    public Map<String, Object> getSongListPages(Page<SongList> songListPage, SongListQueryVo songListQueryVo) {

        QueryWrapper<SongList> songWrapper = new QueryWrapper<>(); //构造条件筛选器
        if (!StringUtils.isEmpty(songListQueryVo.getTitle())){   //根据歌单标题查询
            songWrapper.like("title",songListQueryVo.getTitle());
        }
        if (!StringUtils.isEmpty(songListQueryVo.getIntroduction())){  //歌单介绍模糊匹配
            songWrapper.like("introduction",songListQueryVo.getIntroduction());
        }
        if (!StringUtils.isEmpty(songListQueryVo.getStyle())){  //根据歌单风格
            songWrapper.eq("style",songListQueryVo.getStyle());
        }
        songWrapper.orderByAsc("id");//根据id排序

        Page<SongList> page = this.page(songListPage, songWrapper);

        Map<String,Object> result = new HashMap<>();
        result.put("SongList",page.getRecords()); //获取歌曲集合
        result.put("total", page.getTotal());  //获取记录总数

        return result;
    }

    @Override
    public List<SongList> getAllSongList() {
        return this.list();
    }

    @Override
    public SongList getSongListOneById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean deleteSongListByID(String id) {
        boolean deleteCover = uploadToLocalService.deleteFile(this.getById(id).getPic()); //获得图片地址并删除
        //TODO 同时删除关联表的数据
        return deleteCover && this.removeById(id);
    }

    @Override
    public boolean deleteBatchSongListByIds(String[] params) {
        List<String> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        //获取图片地址
        List<String> collect = baseMapper.selectBatchIds(deleteParams).stream().map(SongList::getPic).collect(Collectors.toList());
        boolean batchFiles = uploadToLocalService.deleteBatchFiles(collect);  //批量删除图片
        return batchFiles && this.removeByIds(deleteParams);
    }

    @Override
    public boolean updateSongList(SongList songList) {
        return this.updateById(songList);
    }

    @Override
    public boolean insertSongListOne(SongList songList) {
        boolean save =false;
        if (null != songList){
            save = this.save(songList);
        }
        return save;
    }

    @Override
    public String uploadSongListCovers(MultipartFile file) {
        String songListCoverPath = uploadToLocalService.uploadFileOne(file, UploadLocalPathConfig.songsSheetPath, "SHEET");
        return null==songListCoverPath?"":songListCoverPath;
    }

    @Override
    public boolean deleteSongListCover(String filePath) {
        return uploadToLocalService.deleteFile(filePath);
    }
}
