package com.cloud.music.dao;

import com.cloud.music.entity.Admin;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * 内容摘要：
 * <p>文件名称: AdminDao.java
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @author ZhangYong
 * @version v1.0
 * @date 2020-11-25  16:58
 */

@Repository
public interface AdminDao {

    /**
     * 方法说明
     * @Title: 判断账户是否存在
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:13
    */
    public Integer queryIsExistsAccount(String name);

    /**
     * 方法说明
     * @Title: 查询后台用户信息(登录对比使用)
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-01 -- 17:12
    */
    public Admin queryAdminInfo(String name);
}
