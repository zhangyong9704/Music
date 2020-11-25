package com.cloud.music.service.impl;

import com.cloud.music.dao.AdminDao;
import com.cloud.music.entity.Admin;
import com.cloud.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内容摘要：
 * <p>文件名称: AdminServiceImpl.java
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @author ZhangYong
 * @version v1.0
 * @date 2020-11-25  16:29
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean getInfoByAccount(Admin admin) {

        int isExists = adminDao.queryIsExistsAccount(admin.getName());
        if (isExists<0){
            return false;
        }
        if ()


        return false;
    }
}
