package com.cloud.music.service;

import com.cloud.music.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 管理员 服务类
 * </p>
 *
 * @author zy
 * @since 2020-12-11
 */
public interface AdminService extends IService<Admin> {

    /**
     * 方法说明
     * @Title: 获取后台用户登录信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:11
     */
    boolean getInfoByAccount(Admin admin);
}
