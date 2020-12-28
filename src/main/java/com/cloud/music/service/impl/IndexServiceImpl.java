package com.cloud.music.service.impl;

import com.cloud.music.mapper.IndexMapper;
import com.cloud.music.service.IndexService;
import com.cloud.music.service.SingerService;
import com.cloud.music.service.SongListService;
import com.cloud.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后天主页 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-11
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Autowired
    SongService songService;

    @Autowired
    SingerService singerService;

    @Autowired
    SongListService songListService;

    @Override
    public Map<String, Integer> getIndexCounts() {
        Map<String,Integer> count = new HashMap<>();
        count.put("songCount",songService.count());
        count.put("singerCount",singerService.count());
        count.put("songListCount",songListService.count());
        return count;
    }
}
