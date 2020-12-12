package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Singer;
import com.cloud.music.entity.vo.SingerQueryVo;
import com.cloud.music.service.SingerService;
import com.cloud.music.utils.ReturnStatusCode;
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
@CrossOrigin
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

    /**
     * 方法说明
     * @Title: 根据id查询歌手
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:22
    */
    @ApiModelProperty("根据id查询歌手")
    @GetMapping("/querySinger/{id}")
    public ReturnUnifiedCode querySingerById(@PathVariable("id") Integer id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法查找");
        }
        Singer singerOne = singerService.getSingerOneById(id);
        return ReturnUnifiedCode.successState().data("singer",singerOne);
    }

    /**
     * 方法说明
     * @Title: 根据ID 删除歌手
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:13
    */
    @ApiModelProperty("根据ID删除歌手")
    @DeleteMapping("deleteSinger/{id}")
    public ReturnUnifiedCode deleteSingerById(@PathVariable("id") Integer id){

        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法删除");
        }
        boolean state = singerService.deleteSinger(id);
        return state?ReturnUnifiedCode.successState().message("删除成功"):
                ReturnUnifiedCode.errorState().message("数据异常");
    }

    /**
     * 方法说明
     * @Title: 根据id批量删除歌手
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:14
    */
    @ApiModelProperty("根据id批量删除歌手")
    @DeleteMapping("deleteBatch/{params}")
    public ReturnUnifiedCode deleteBatchSingerByIds(@PathVariable Integer[] params){
        if (params.length<=0){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"删除ids为空,无法删除");
        }
        boolean deleteState = singerService.deleteBatchSingerByIds(params);
        return deleteState?ReturnUnifiedCode.successState().message("批量删除成功"):
                ReturnUnifiedCode.errorState().message("批量删除失败");
    }

    /**
     * 方法说明
     * @Title: 根据id 修改歌手信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:56
    */
    @ApiModelProperty("修改Singer信息")
    @PutMapping("/update")
    public ReturnUnifiedCode updateSingeById(@RequestBody Singer singer){
        if (null == singer){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前对象为空,修改失败");
        }
        boolean update = singerService.updateSinger(singer);
        return update?ReturnUnifiedCode.successState().message("修改成功"):ReturnUnifiedCode.errorState().message("修改失败");
    }

    /**
     * 方法说明
     * @Title: 添加歌手信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
    */
    @ApiModelProperty("添加歌手信息")
    @PostMapping("/add")
    public ReturnUnifiedCode insertSingerInfo(@RequestBody Singer singer){
        boolean insert = singerService.insertSingerOne(singer);
        return insert?ReturnUnifiedCode.successState().message("添加成功"):ReturnUnifiedCode.errorState().message("添加失败");
    }
}

