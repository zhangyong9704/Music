package com.cloud.music.controller.front.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.entity.Song;
import com.cloud.music.entity.SongList;
import com.cloud.music.entity.vo.SongListQueryVo;
import com.cloud.music.entity.vo.SongQueryVo;
import com.cloud.music.service.SongListService;
import com.cloud.music.service.SongService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName SearchFrontController.java
 * @Description  前端搜索控制器
 * @CreateDate 2021-01-11  21:37:46
 */
@RestController
@RequestMapping("/search-front")
@Api(tags = "SearchFrontController")
@CrossOrigin //跨域
public class SearchFrontController {

    @Autowired
    SongService songService;

    @Autowired
    SongListService songListService;

    @GetMapping("/song-keyword/{currentPage}/{limitSize}/{keyword}")
    public ReturnUnifiedCode SearchSongByKeyWords(@PathVariable long currentPage,
                                                  @PathVariable long limitSize,
                                                  @PathVariable String keyword){
        Page<Song> songsPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> songList =  songService.getSongPages(songsPage,new SongQueryVo().setName(keyword))  ;

        return songList.size()>0?ReturnUnifiedCode.successState().data("data",songList.get("Songs")).data("total",songList.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }


    @GetMapping("/song-list-keyword/{currentPage}/{limitSize}/{keyword}")
    public ReturnUnifiedCode SearchSongListByKeyWords(@PathVariable long currentPage,
                                                      @PathVariable long limitSize,
                                                      @PathVariable String keyword){
        Page<SongList> songListPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> songList =  songListService.getSongListPages(songListPage,new SongListQueryVo().setTitle(keyword))  ;

        return songList.size()>0?ReturnUnifiedCode.successState().data("data",songList.get("SongList")).data("total",songList.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }


}
