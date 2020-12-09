package com.cloud.music.service.impl;

import com.cloud.music.entity.Singer;
import com.cloud.music.mapper.SingerMapper;
import com.cloud.music.service.SingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-09
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {

}
