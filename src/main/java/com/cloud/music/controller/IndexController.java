package com.cloud.music.controller;

import com.cloud.music.entity.vo.IndexChartsQueryVo;
import com.cloud.music.service.IndexService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 后台 主页控制器
 * </p>
 *
 * @author zy
 * @version v1.0
 * @since 2020-12-28
 */
@Api(tags = "IndexController")
@RestController
@RequestMapping("/index")
@CrossOrigin
public class IndexController {

    @Autowired
    IndexService indexService;

    /**
     * 方法说明
     * @Title: 主页歌手、歌曲、歌单数量查询
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("主页歌手、歌曲、歌单数量查询")
    @GetMapping("/counts")
    public ReturnUnifiedCode getIndexCounts(){

        Map<String, Integer> indexCounts = indexService.getIndexCounts();
        return ReturnUnifiedCode.successState().data("count",indexCounts);
    }

    /**
     * 方法说明
     * @Title: 获得歌手国际地区分布
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("获得歌手国际地区分布")
    @GetMapping("/singer_region")
    public ReturnUnifiedCode getRegionalDistributionOfSingers(){

        IndexChartsQueryVo region = indexService.getSingersRegionalDistribution();

        return ReturnUnifiedCode.successState().data("singer_region",region);
    }

    /**
     * 方法说明
     * @Title: 获得歌手类型组合
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("获得歌手类型组合")
    @GetMapping("/singer_type")
    public ReturnUnifiedCode getCombinationTypeOfSingers(){

        IndexChartsQueryVo singer_type = indexService.getSingersCombinationType();

        return ReturnUnifiedCode.successState().data("singer_type",singer_type);
    }

    /**
     * 方法说明
     * @Title: 获得歌单风格比例
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("获得歌单风格比例")
    @GetMapping("/song_list_type")
    public ReturnUnifiedCode getRegionalDistributionOfSongList(){

        IndexChartsQueryVo song_list_type = indexService.getSongListRegionalDistribution();

        return ReturnUnifiedCode.successState().data("song_list_type",song_list_type);
    }

    /**
     * 方法说明
     * @Title: 获得歌单风格比例
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("获得歌单高分榜")
    @GetMapping("/song_list_score")
    public ReturnUnifiedCode getHighScoreSongList(){

        IndexChartsQueryVo song_list_score = indexService.getSongListHighScore();

        return ReturnUnifiedCode.successState().data("song_list_score",song_list_score);
    }

    /**
     * 方法说明
     * @Title: 获得用户性别分布
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("获得用户性别分布")
    @GetMapping("/user_gender")
    public ReturnUnifiedCode getGenderDistributionOfUsers(){

        IndexChartsQueryVo user_gender = indexService.getUsersGenderDistribution();

        return ReturnUnifiedCode.successState().data("user_gender",user_gender);
    }


    /**
     * 方法说明
     * @Title: 获得用户地区分布
     * @Description TODO
     * @return
     * @date 2020-12-28 -- 10:37
     */
    @ApiOperation("获得用户地区分布")
    @GetMapping("/user_region")
    public ReturnUnifiedCode getRegionalDistributionOfUsers(){

        IndexChartsQueryVo user_region = indexService.getUsersRegionalDistribution();

        return ReturnUnifiedCode.successState().data("user_region",user_region);
    }

}
