package com.cloud.music.service.impl;

import com.cloud.music.entity.Song;
import com.cloud.music.mapper.SongMapper;
import com.cloud.music.service.SongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌曲 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-16
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {

}
