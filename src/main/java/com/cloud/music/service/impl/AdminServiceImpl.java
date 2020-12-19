package com.cloud.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.entity.Admin;
import com.cloud.music.mapper.AdminMapper;
import com.cloud.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-12-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminDao;

    @Override
    public boolean getInfoByAccount(Admin admin) {

        //判断用户账户是否存在
        Integer isExists = adminDao.queryIsExistsAccount(admin.getUsername());
        if (isExists==null || isExists<0){
            return false;
        }
        //对比账户和密码信息
        Admin adminInfo = adminDao.queryAdminInfo(admin.getUsername());

        return adminInfo.getPassword().equalsIgnoreCase(admin.getPassword());
    }
}
