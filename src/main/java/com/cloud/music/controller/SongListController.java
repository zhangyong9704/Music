package com.cloud.music.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

