package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.Singer;
import com.cloud.music.entity.vo.SingerQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌手 服务类
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
public interface SingerService extends IService<Singer> {

    /** 
     * 方法说明
     * @Title: 获取歌手分页数据信息
     * @Description TODO
     * @Param 
     * @return 
     * @date 2020-12-11 -- 14:45
    */
    Map<String,Object> getSingerPages(Page<Singer> singerPage, SingerQueryVo singerQueryVo);

    /** 
     * 方法说明
     * @Title: 查询歌手全部信息(没有条件)
     * @Description TODO
     * @Param 
     * @return 
     * @date 2020-12-11 -- 15:45
    */
    List<Singer> getAllSingers();
}
