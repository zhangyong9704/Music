package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.Consumer;
import com.cloud.music.entity.vo.ConsumerQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端用户 服务类
 * </p>
 *
 * @author zy
 * @since 2021-01-22
 */
public interface ConsumerService extends IService<Consumer> {

    /**
     * 方法说明
     * @Title: 获取用户分页数据信息
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    Map<String, Object> getConsumerPages(Page<Consumer> consumerPage, ConsumerQueryVo consumerQueryVo);

    /**
     * 方法说明
     * @Title: 获取用户所有信息
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    List<Consumer> getAllConsumers();

    /**
     * 方法说明
     * @Title: 根据ID获取单个用户
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    Consumer getConsumerOneById(Integer id);

    /**
     * 方法说明
     * @Title: 根据ID删除单个用户
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    boolean deleteConsumer(String id);

    /**
     * 方法说明
     * @Title: 根据ID批量删除用户
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    boolean deleteBatchConsumerByIds(String[] params);

    /**
     * 方法说明
     * @Title: 根据ID更新用户信息
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    boolean updateConsumer(Consumer consumer);

    /**
     * 方法说明
     * @Title: 新增用户信息
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    boolean insertConsumerOne(Consumer consumer);

    /**
     * 方法说明
     * @Title: 上传用户封面
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    String uploadConsumerFileOne(MultipartFile file);

    /**
     * 方法说明
     * @Title: 删除上次上传用户封面
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    boolean deletePreviousConsumerCover(String filePath);




    /*==========================前端调用部分===========================================*/
    /**
     * 方法说明
     * @Title: 验证用户登录信息
     * @Description TODO
     * @Param
     * @return
     * @date 2021-01-23 -- 09:45
     */
    Consumer verifyConsumerLogin(Consumer consumer);




}
