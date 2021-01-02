package com.cloud.music.service;

import com.cloud.music.entity.vo.IndexChartsQueryVo;

import java.util.Map;

/**
 * <p>
 * 后台主页 服务类
 * </p>
 *
 * @author zy
 * @since 2020-12-11
 */
public interface IndexService {

    /**
     * 方法说明
     * @Title: 主页歌手、歌曲、歌单数量查询
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-18 -- 17:14
     */
   Map<String,Integer> getIndexCounts();

    /**
     * 方法说明
     * @Title: 获得歌手国际地区分布
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-18 -- 17:14
     */
    IndexChartsQueryVo getSingersRegionalDistribution();

    /**
     * 方法说明
     * @Title: 获得歌手类型组合
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-18 -- 17:14
     */
    IndexChartsQueryVo getSingersCombinationType();

    /**
     * 方法说明
     * @Title: 获得歌单风格比例
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-18 -- 17:14
     */
    IndexChartsQueryVo getSongListRegionalDistribution();

   /**
    * 方法说明
    * @Title: 获得歌单高分榜
    * @Description TODO
    * @Param
    * @return
    * @date 2020-12-18 -- 17:14
    */
    IndexChartsQueryVo getSongListHighScore();

    /**
     * 方法说明
     * @Title: 获得用户性别分布
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-18 -- 17:14
     */
    IndexChartsQueryVo getUsersGenderDistribution();

    /**
     * 方法说明
     * @Title: 获得用户地区分布
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-18 -- 17:14
     */
    IndexChartsQueryVo getUsersRegionalDistribution();


}
