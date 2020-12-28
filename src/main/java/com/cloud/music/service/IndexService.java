package com.cloud.music.service;

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

}
