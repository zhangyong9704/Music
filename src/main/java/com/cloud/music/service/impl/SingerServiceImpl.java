package com.cloud.music.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.entity.Singer;
import com.cloud.music.entity.vo.SingerQueryVo;
import com.cloud.music.mapper.SingerMapper;
import com.cloud.music.service.SingerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

    @Override
    public Map<String,Object> getSingerPages(Page<Singer> singerPage, SingerQueryVo singerQueryVo) {

        QueryWrapper<Singer>  wrapper =  new QueryWrapper<>(); //构造条件筛选器
        if (!StringUtils.isEmpty(singerQueryVo.getName())){   //根据姓名查询
            wrapper.like("name",singerQueryVo.getName());
        }
        if (!StringUtils.isEmpty(String.valueOf(singerQueryVo.getSex()))){ //o-女 1-男 根据性别查询
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
    public boolean deleteSinger(Integer id) {
        return this.removeById(id);
    }

    @Override
    public boolean deleteBatchSingerByIds(Integer[] params) {
        List<Integer> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        return this.removeByIds(deleteParams);
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
}
