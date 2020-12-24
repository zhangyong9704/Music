package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.SongList;
import com.cloud.music.entity.vo.SongListQueryVo;
import com.cloud.music.service.SongListService;
import com.cloud.music.utils.ReturnStatusCode;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.cloud.music.utils.ReturnStatusCode.ERROR_STATUS;

/**
 * <p>
 * 歌单 前端控制器
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Api(tags = "SongListController")
@CrossOrigin
@RestController
@RequestMapping("/song-list")
public class SongListController {


    @Autowired
    SongListService songListService;

    /**
     * 方法说明
     * @Title: 带条件的的歌单信息分页查询
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 14:37
     */
    @ApiOperation("条件歌单信息分页查询")
    @PostMapping("/querySongList/{currentPage}/{limitSize}")
    public ReturnUnifiedCode selectSongListPages(@RequestBody(required = false) SongListQueryVo songListQueryVo,
                                                 @PathVariable long currentPage,
                                                 @PathVariable long limitSize){

        Page<SongList> songListPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> songList =  songListService.getSongListPages(songListPage,songListQueryVo)  ;

        return songList.size()>0?ReturnUnifiedCode.successState().data("songList",songList.get("SongList")).data("total",songList.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }

    /**
     * 方法说明
     * @Title: 无条件查询所有歌单信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 15:21
     */
    @ApiOperation("无条件查询所有歌单信息")
    @GetMapping("/querySongList")
    public ReturnUnifiedCode queryAllSongList(){

        List<SongList> songsList = songListService.getAllSongList();

        return ReturnUnifiedCode.successState().data("songList",songsList).data("total",songsList.size());

    }

    /**
     * 方法说明
     * @Title: 根据id查询歌单详情
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-22 -- 17:22
     */
    @ApiOperation("根据id查询歌单")
    @GetMapping("/querySongList/{id}")
    public ReturnUnifiedCode querySongListById(@PathVariable("id") Integer id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法查找");
        }
        SongList songListOne = songListService.getSongListOneById(id);
        return ReturnUnifiedCode.successState().data("songList",songListOne);
    }

    /**
     * 方法说明
     * @Title: 根据ID 删除歌单
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:13
     */
    @ApiOperation("根据ID删除歌单")
    @DeleteMapping("/deleteSongList/{id}")
    public ReturnUnifiedCode deleteSongListById(@PathVariable("id") String id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法删除");
        }
        boolean state = songListService.deleteSongListByID(id);
        return state?ReturnUnifiedCode.successState().message("删除成功"):
                ReturnUnifiedCode.errorState().message("数据异常");
    }

    /**
     * 方法说明
     * @Title: 根据id批量删除歌单
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:14
     */
    @ApiOperation("根据id批量删除歌单")
    @DeleteMapping("/deleteBatch/{params}")
    public ReturnUnifiedCode deleteBatchSongListByIds(@PathVariable String[] params){
        if (params.length<=0){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"删除ids为空,无法删除");
        }
        boolean deleteState = songListService.deleteBatchSongListByIds(params);
        return deleteState?ReturnUnifiedCode.successState().message("批量删除成功"):
                ReturnUnifiedCode.errorState().message("批量删除失败");
    }

    /**
     * 方法说明
     * @Title: 修改SongList信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:56
     */
    @ApiOperation("修改SongList信息")
    @PutMapping("/update")
    public ReturnUnifiedCode updateSongListById(@RequestBody SongList songList){
        if (null == songList){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前对象为空,修改失败");
        }
        boolean update = songListService.updateSongList(songList);
        return update?ReturnUnifiedCode.successState().message("修改成功"):
                ReturnUnifiedCode.errorState().message("修改失败");
    }

    /**
     * 方法说明
     * @Title: 添加歌单信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation("添加歌单信息")
    @PostMapping("/add")
    public ReturnUnifiedCode insertSongListOne(@RequestBody SongList songList){
        boolean insert = songListService.insertSongListOne(songList);
        return insert?ReturnUnifiedCode.successState().message("添加成功"):
                ReturnUnifiedCode.errorState().message("添加失败");
    }

    /**
     * 方法说明
     * @Title: 上传歌单封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "上传歌单封面")
    @PostMapping(value = "/upload-cover", consumes = "multipart/*",  headers = "content-type=multipart/form-data")
    public ReturnUnifiedCode uploadSongListCover(MultipartFile file){
        if(file.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传歌单封面文件为空");
        }
        String uploadPath = songListService.uploadSongListCovers(file);
        return null!=uploadPath? ReturnUnifiedCode.successState().data("path",uploadPath):
                ReturnUnifiedCode.errorState().message("歌单封面文件上传失败");
    }


    /**
     * 方法说明
     * @Title: 删除上次上传歌曲封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "删除上次上传歌曲封面")
    @PostMapping("/delete-upload")
    public ReturnUnifiedCode uploadDeletePreviousSongListCover(@RequestParam("filePath") String filePath){
        if(filePath.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"删除文件地址为空");
        }
        boolean uploadPath = songListService.deleteSongListCover(filePath);
        return uploadPath?ReturnUnifiedCode.successState().message("删除文件成功"):
                ReturnUnifiedCode.errorState().message("文件上传失败");
    }


}

