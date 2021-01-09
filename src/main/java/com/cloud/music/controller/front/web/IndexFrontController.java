package com.cloud.music.controller.front.web;

import com.cloud.music.entity.vo.IndexSingerQueryVo;
import com.cloud.music.entity.vo.IndexSongListQueryVo;
import com.cloud.music.service.IndexService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName IndexFrontController.java
 * @Description 前台主页请求歌单、歌曲信息
 * @CreateDate 2021-01-06  22:11:12
 */

@RestController
@RequestMapping("/index-front")
@Api(tags = "IndexFrontController")
@CrossOrigin //跨域
public class IndexFrontController {

    @Autowired
    private IndexService indexService;

    /**
     * 方法说明
     * @Title: 获取首页TopTenSingers
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "获取首页TopTenSingers")
    @GetMapping("/top-singer")
    public ReturnUnifiedCode topTenSingers() {

        List<IndexSingerQueryVo> singerList = indexService.getTopsTenSingerS();

        return (null != singerList)?ReturnUnifiedCode.successState().data("data",singerList):
                ReturnUnifiedCode.errorState().message("数据异常，获取banner不存在").data("data",null);
    }


    /**
     * 方法说明
     * @Title: 获取首页TopTenSingers
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "获取首页TopTenSongList")
    @GetMapping("/top-songList")
    public ReturnUnifiedCode topTenSongList() {

        List<IndexSongListQueryVo> songList = indexService.getTopsTenSongList();

        return (null != songList)?ReturnUnifiedCode.successState().data("data",songList):
                ReturnUnifiedCode.errorState().message("数据异常，获取banner不存在").data("data",null);
    }

}
