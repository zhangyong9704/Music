package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.entity.Singer;
import com.cloud.music.entity.vo.SingerQueryVo;
import com.cloud.music.service.SingerService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 歌手 前端控制器
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
@Api(tags = "SingerController")
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    SingerService singerService;

    /**
     * 方法说明
     * @Title: 带条件的的歌手信息分页查询
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 14:37
    */
    @ApiModelProperty(name = "条件的的歌手信息分页查询")
    @PostMapping("/querySingers/{currentPage}/{limitSize}")
    public ReturnUnifiedCode selectSingerPages(@RequestBody(required = false) SingerQueryVo singerQueryVo,
                                               @PathVariable long currentPage,
                                               @PathVariable long limitSize){

        Page<Singer> singerPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> singerList =  singerService.getSingerPages(singerPage,singerQueryVo)  ;

        return singerList.size()>0?ReturnUnifiedCode.successState().data("singer",singerList.get("Singer")).data("total",singerList.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }

    /**
     * 方法说明
     * @Title: 无条件查询所有歌手信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:21
    */
    @ApiModelProperty("无条件查询所有歌手信息")
    @GetMapping("/querySingers")
    public ReturnUnifiedCode queryAllSinger(){

        List<Singer> singerList = singerService.getAllSingers();

        return ReturnUnifiedCode.successState().data("singer",singerList);

    }

}

