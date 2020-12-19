package com.cloud.music.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.entity.Singer;
import com.cloud.music.entity.vo.SingerQueryVo;
import com.cloud.music.mapper.SingerMapper;
import com.cloud.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
@Service
@Transactional
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Autowired
    UploadToLocalService uploadToLocalService;

    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;

    @Override
    public Map<String,Object> getSingerPages(Page<Singer> singerPage, SingerQueryVo singerQueryVo) {

        QueryWrapper<Singer>  wrapper =  new QueryWrapper<>(); //构造条件筛选器
        if (!StringUtils.isEmpty(singerQueryVo.getName())){   //根据姓名查询
            wrapper.like("name",singerQueryVo.getName());
        }
        if (null!=singerQueryVo.getSex()){ //o-女 1-男 根据性别查询
            wrapper.eq("sex",singerQueryVo.getSex());
        }
        if (!StringUtils.isEmpty(singerQueryVo.getLocation())){  //根据地区查询
            wrapper.like("location",singerQueryVo.getLocation());
        }
        wrapper.orderByAsc("id");  //根据id排序

        Page<Singer> page = this.page(singerPage, wrapper);

        Map<String,Object> result = new HashMap<>();
        result.put("Singer",page.getRecords()); //获取集合
        result.put("total", page.getTotal());  //获取记录总数

        return result;
    }

    @Override
    public List<Singer> getAllSingers() {
        return this.list();
    }

    @Override
    public Singer getSingerOneById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean deleteSinger(String id) {
        boolean deleteFile = uploadToLocalService.deleteFile(this.getById(id).getPic()); //获得图片地址并删除
        return deleteFile && this.removeById(id);
    }

    @Override
    public boolean deleteBatchSingerByIds(String[] params) {
        List<String> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        //获取图片地址
        List<String> collect = baseMapper.selectBatchIds(deleteParams).stream().map(Singer::getPic).collect(Collectors.toList());
        boolean batchFiles = uploadToLocalService.deleteBatchFiles(collect);  //批量删除图片
        return batchFiles && this.removeByIds(deleteParams);
    }

    @Override
    public boolean updateSinger(Singer singer) {
        return this.updateById(singer);
    }

    @Override
    public boolean insertSingerOne(Singer singer) {
        boolean save =false;
        if (null != singer){
            save = this.save(singer);
        }
        return save;
    }

    @Override
    public String uploadSingerFileOne(MultipartFile file) {
        String singerCoverPath = uploadToLocalService.uploadFileOne(file, UploadLocalPathConfig.singerCoverPath, "SINGER");
        return null==singerCoverPath?"":singerCoverPath;
    }

    @Override
    public boolean deletePreviousSingerCover(String filePath) {
        return uploadToLocalService.deleteFile(filePath);
    }
}
