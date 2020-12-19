package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.Singer;
import com.cloud.music.entity.vo.SingerQueryVo;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 方法说明
     * @Title: 根据id查询单个歌手信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:27
    */
    Singer getSingerOneById(Integer id);

    /**
     * 方法说明
     * @Title: 根据id单个删除singer
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:40
    */
    boolean deleteSinger(String id);


    /**
     * 方法说明
     * @Title: 批量删除歌手信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:05
    */
    boolean deleteBatchSingerByIds(String[] params);
    /** 
     * 方法说明
     * @Title: 更新歌手信息
     * @Description TODO
     * @Param 
     * @return 
     * @date 2020-12-11 -- 16:58
    */
    boolean updateSinger(Singer singer);


    /**
     * 方法说明
     * @Title: 添加歌手信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:29
    */
    boolean insertSingerOne(Singer singer);

    /**
     * 方法说明
     * @Title: 上传歌手封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    String uploadSingerFileOne(MultipartFile file);

    /**
     * 方法说明
     * @Title: 删除上次上传歌手封面(编辑时)
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    boolean deletePreviousSingerCover(String filePath);
}
