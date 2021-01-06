package com.cloud.music.mapper;

import com.cloud.music.entity.Singer;
import com.cloud.music.entity.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台主页 Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2020-12-28
 */
@Repository
public interface IndexMapper {

    /**
     * 方法说明
     * @Title: 获得歌手国际地区分布
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Map<String,Object>> getSingersRegionalDistribution();

    /**
     * 方法说明
     * @Title: 获得歌手类型组合
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Map<String, Object>> getSingersCombinationType();

    /**
     * 方法说明
     * @Title: 获得歌单风格比例
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Map<String, Object>> getSongListRegionalDistribution();

    /**
     * 方法说明
     * @Title: 获得用户性别分布
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Map<String, Object>> getUsersGenderDistribution();

    /**
     * 方法说明
     * @Title: 获得用户地区分布
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Map<String, Object>> getUsersRegionalDistribution();

    /**
     * 方法说明
     * @Title: 获得歌单高分榜
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Map<String, Object>> getSongListHighScore();

    /**
     * 方法说明
     * @Title: 根据前十的评论获取优秀的歌手
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<Singer> getTopTenSingerByComment();

    /**
     * 方法说明
     * @Title: 评分前十的歌单
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    List<SongList> getTopTenSongList();
}