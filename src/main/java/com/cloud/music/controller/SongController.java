package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Song;
import com.cloud.music.entity.vo.SongQueryVo;
import com.cloud.music.service.SongService;
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
 * 歌曲 前端控制器
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Api(tags = "SongsController")
@CrossOrigin
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    /**
     * 方法说明
     * @Title: 带条件的的歌曲信息分页查询
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 14:37
     */
    @ApiOperation("条件歌曲信息分页查询")
    @PostMapping("/querySongs/{currentPage}/{limitSize}")
    public ReturnUnifiedCode selectSongsPages(@RequestBody(required = false) SongQueryVo songQueryVo,
                                               @PathVariable long currentPage,
                                               @PathVariable long limitSize){

        Page<Song> songsPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> songList =  songService.getSongPages(songsPage,songQueryVo)  ;

        return songList.size()>0?ReturnUnifiedCode.successState().data("songs",songList.get("Songs")).data("total",songList.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }

    /**
     * 方法说明
     * @Title: 无条件查询所有歌曲信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:21
     */
    @ApiOperation("无条件查询所有歌曲信息")
    @GetMapping("/querySongs")
    public ReturnUnifiedCode queryAllSongs(){

        List<Song> songsList = songService.getAllSongs();

        return ReturnUnifiedCode.successState().data("song",songsList);

    }

    /**
     * 方法说明
     * @Title: 根据id查询歌曲详情
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:22
     */
    @ApiOperation("根据singer_id查询歌曲")
    @GetMapping("/querySong/{id}")
    public ReturnUnifiedCode querySongsById(@PathVariable("id") Integer id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法查找");
        }
        Song songOne = songService.getSongOneById(id);
        return ReturnUnifiedCode.successState().data("song",songOne);
    }

    /**
     * 方法说明
     * @Title: 根据ID 删除歌曲
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:13
     */
    @ApiOperation("根据ID删除歌曲")
    @DeleteMapping("/deleteSong/{id}")
    public ReturnUnifiedCode deleteSongsById(@PathVariable("id") String id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法删除");
        }
        boolean state = songService.deleteSongByID(id);
        return state?ReturnUnifiedCode.successState().message("删除成功"):
                ReturnUnifiedCode.errorState().message("数据异常");
    }

    /**
     * 方法说明
     * @Title: 根据id批量删除歌曲
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:14
     */
    @ApiOperation("根据id批量删除歌曲")
    @DeleteMapping("/deleteBatch/{params}")
    public ReturnUnifiedCode deleteBatchSongsByIds(@PathVariable String[] params){
        if (params.length<=0){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"删除ids为空,无法删除");
        }
        boolean deleteState = songService.deleteBatchSongsByIds(params);
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
    @ApiOperation("修改Songs信息")
    @PutMapping("/update")
    public ReturnUnifiedCode updateSongsById(@RequestBody Song songs){
        if (null == songs){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前对象为空,修改失败");
        }
        boolean update = songService.updateSongs(songs);
        return update?ReturnUnifiedCode.successState().message("修改成功"):ReturnUnifiedCode.errorState().message("修改失败");
    }

    /**
     * 方法说明
     * @Title: 添加歌曲信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation("添加歌手信息")
    @PostMapping("/add")
    public ReturnUnifiedCode insertSongsInfo(@RequestBody Song song){
        boolean insert = songService.insertSongOne(song);
        return insert?ReturnUnifiedCode.successState().message("添加成功"):ReturnUnifiedCode.errorState().message("添加失败");
    }

    /**
     * 方法说明
     * @Title: 上传歌曲封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "上传歌曲封面")
    @PostMapping(value = "/upload-cover", consumes = "multipart/*",  headers = "content-type=multipart/form-data")
    public ReturnUnifiedCode uploadSongsCover(MultipartFile file){
        if(file.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传歌曲封面文件为空");
        }
        String uploadPath = songService.uploadSongsCovers(file);
        return null!=uploadPath? ReturnUnifiedCode.successState().data("path",uploadPath):
                ReturnUnifiedCode.errorState().message("歌曲封面文件上传失败");
    }

    /**
     * 方法说明
     * @Title: 上传歌曲
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "上传歌曲")
    @PostMapping(value = "/upload-file", consumes = "multipart/*",  headers = "content-type=multipart/form-data")
    public ReturnUnifiedCode uploadSongsFiles(MultipartFile file){
        if(file.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传音乐文件为空");
        }
        String uploadPath = songService.uploadSongsFile(file);
        return null!=uploadPath? ReturnUnifiedCode.successState().data("path",uploadPath):
                ReturnUnifiedCode.errorState().message("音乐文件上传失败");
    }

    @ApiOperation(value = "删除上次上传歌手封面")
    @PostMapping("/delete-upload")
    public ReturnUnifiedCode uploadDeletePreviousSongsCover(@RequestParam("filePath") String filePath){
        if(filePath.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"删除文件地址为空");
        }
        boolean uploadPath = songService.deleteSongsCoverAndFiles(filePath);
        return uploadPath?ReturnUnifiedCode.successState().message("删除文件成功"):
                ReturnUnifiedCode.errorState().message("文件上传失败");
    }

}

