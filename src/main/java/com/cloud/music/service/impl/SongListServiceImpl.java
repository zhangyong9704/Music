package com.cloud.music.service.impl;

import com.cloud.music.entity.SongList;
import com.cloud.music.mapper.SongListMapper;
import com.cloud.music.service.SongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

}
