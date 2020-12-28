package com.cloud.music.controller;

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

}
