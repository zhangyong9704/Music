package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Song;
import com.cloud.music.entity.vo.ListSongsQueryVo;
import com.cloud.music.service.ListSongService;
import com.cloud.music.utils.ReturnStatusCode;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 歌单包含歌曲列表 前端控制器
 * </p>
 *
 * @author zy
 * @since 2020-12-22
 */
@Api(tags = "ListSongController")
@CrossOrigin
@RestController
@RequestMapping("/list-song")
public class ListSongController {

    @Autowired
    ListSongService listSongService;

    /**
     * 方法说明
     * @Title: 带条件的的歌单下所有歌曲信息分页查询
     * @Description TODO
     * @Param ListSong 用于接收歌单id使用
     * @Param SongQueryVo 用于筛选歌曲搜索条件
     * @return
     * @date 2020-12-22 -- 14:37
     */
    @ApiOperation("条件歌曲信息分页查询")
    @PostMapping("/queryListSongs/{currentPage}/{limitSize}")
    public ReturnUnifiedCode selectListSongsPages(@RequestBody(required = false) ListSongsQueryVo listSongsQueryVo ,
                                                  @PathVariable long currentPage,
                                                  @PathVariable long limitSize){

        Page<Song> listSongsPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> listSongs =  listSongService.getListSongPages(listSongsPage,listSongsQueryVo)  ;

        return listSongs.size()>0?ReturnUnifiedCode.successState().data("listSongs",listSongs.get("listSongs")).data("total",listSongs.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }


    /**
     * 方法说明
     * @Title: 根据ID 删除歌单下的歌曲
     * @Description TODO
     * @Param id 歌单id  songId 歌曲对应的id
     * @return
     * @date 2020-12-11 -- 16:13
     */
    @ApiOperation("根据ID删除歌曲")
    @DeleteMapping("/deleteListSong/{id}/{songId}")
    public ReturnUnifiedCode deleteSongsById(@PathVariable("id") String id,@PathVariable("songId") String songId){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法删除");
        }
        if (null == songId){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前songId为空,无法删除");
        }
        boolean state = listSongService.deleteListSongByID(id,songId);
        return state?ReturnUnifiedCode.successState().message("删除成功"):
                ReturnUnifiedCode.errorState().message("数据异常");
    }

    /**
     * 方法说明
     * @Title: 根据id批量删除歌曲
     * @Description TODO
     * @Param id 歌单id
     * @return
     * @date 2020-12-11 -- 17:14
     */
    @ApiOperation("根据id批量删除歌曲")
    @DeleteMapping("/deleteBatch/{params}/{id}")
    public ReturnUnifiedCode deleteBatchSongsByIds(@PathVariable String[] params,@PathVariable("id") String id){
        if (params.length<=0){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"删除ids为空,无法删除");
        }
        if (id.isEmpty()){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"歌单id为空,无法删除");
        }
        boolean deleteState = listSongService.deleteBatchListSongsByIds(params,id);
        return deleteState?ReturnUnifiedCode.successState().message("批量删除成功"):
                ReturnUnifiedCode.errorState().message("批量删除失败");
    }


    /**
     * 方法说明
     * @Title: 添加歌曲信息
     * @Description TODO unfinished
     * @Param song 歌曲表单信息  id 歌单id
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation("添加歌单信息")
    @PostMapping("/add/{id}")
    public ReturnUnifiedCode insertSongsInfo(@RequestBody Song song,@PathVariable("id") String id){
        if (null == song){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"新增歌单信息为空");
        }
        if (id.isEmpty()){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"歌单id为空,无法新增");
        }
        boolean insert = listSongService.insertListSongsOne(song,id);
        return insert?ReturnUnifiedCode.successState().message("添加成功"):ReturnUnifiedCode.errorState().message("添加失败");
    }

}

