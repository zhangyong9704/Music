package com.cloud.music.service.impl;

import com.cloud.music.entity.Comment;
import com.cloud.music.mapper.CommentMapper;
import com.cloud.music.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author zy
 * @since 2021-01-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
