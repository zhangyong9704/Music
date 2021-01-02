package com.cloud.music.service.impl;

import com.cloud.music.entity.vo.IndexChartsQueryVo;
import com.cloud.music.mapper.IndexMapper;
import com.cloud.music.service.IndexService;
import com.cloud.music.service.SingerService;
import com.cloud.music.service.SongListService;
import com.cloud.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public IndexChartsQueryVo getSingersRegionalDistribution() {

        List<Map<String,Object>> singer_region = indexMapper.getSingersRegionalDistribution();

        IndexChartsQueryVo indexChartsQueryVo = new IndexChartsQueryVo();
        indexChartsQueryVo.setColumns(new String[]{"singer_region","count"});
        indexChartsQueryVo.setRows(singer_region);

        return indexChartsQueryVo;
    }

    @Override
    public IndexChartsQueryVo getSingersCombinationType() {

        List<Map<String,Object>> singer_type = indexMapper.getSingersCombinationType();

        IndexChartsQueryVo indexChartsQueryVo = new IndexChartsQueryVo();
        indexChartsQueryVo.setColumns(new String[]{"type","count"});
        indexChartsQueryVo.setRows(singer_type);

        return indexChartsQueryVo;
    }

    @Override
    public IndexChartsQueryVo getSongListRegionalDistribution() {

        List<Map<String,Object>> song_list_region = indexMapper.getSongListRegionalDistribution();

        IndexChartsQueryVo indexChartsQueryVo = new IndexChartsQueryVo();
        indexChartsQueryVo.setColumns(new String[]{"style","count"});
        indexChartsQueryVo.setRows(song_list_region);

        return indexChartsQueryVo;
    }

    @Override
    public IndexChartsQueryVo getSongListHighScore() {

        List<Map<String,Object>> song_list_score = indexMapper.getSongListHighScore();

        IndexChartsQueryVo indexChartsQueryVo = new IndexChartsQueryVo();
        indexChartsQueryVo.setColumns(new String[]{"title","score"});
        indexChartsQueryVo.setRows(song_list_score);

        return indexChartsQueryVo;
    }

    @Override
    public IndexChartsQueryVo getUsersGenderDistribution() {

        List<Map<String,Object>> user_gender = indexMapper.getUsersGenderDistribution();

        IndexChartsQueryVo indexChartsQueryVo = new IndexChartsQueryVo();
        indexChartsQueryVo.setColumns(new String[]{"type","count"});
        indexChartsQueryVo.setRows(user_gender);

        return indexChartsQueryVo;
    }

    @Override
    public IndexChartsQueryVo getUsersRegionalDistribution() {

        List<Map<String,Object>> user_region = indexMapper.getUsersRegionalDistribution();

        IndexChartsQueryVo indexChartsQueryVo = new IndexChartsQueryVo();
        indexChartsQueryVo.setColumns(new String[]{"location","count"});
        indexChartsQueryVo.setRows(user_region);

        return indexChartsQueryVo;
    }
}
