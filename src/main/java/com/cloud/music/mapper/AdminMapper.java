package com.cloud.music.mapper;

import com.cloud.music.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2020-12-11
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 方法说明
     * @Title: 判断账户是否存在
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
     */
    public Integer queryIsExistsAccount(String username);

    /**
     * 方法说明
     * @Title: 查询后台用户信息(登录对比使用)
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:12
     */
    public Admin queryAdminInfo(String username);
}
