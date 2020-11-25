package com.cloud.music.controller.login;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.cloud.music.entity.Admin;
import com.cloud.music.service.AdminService;
import com.cloud.music.utils.ResultUniformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容摘要：
 * <p>文件名称: AdminController.java
 *
 * @author ZhangYong
 * @version v1.0
 * @date 2020-11-25  16:26
 */
@RestController("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login//status")
    public ResultUniformDate loginStatus(Admin admin, HttpRequest request){

        if (null == admin){
            System.out.println("用户信息未传入");
            return null;
        }

        boolean isEnableLogin = adminService.getInfoByAccount(admin);

        return isEnableLogin?new ResultUniformDate():null;
    }

}
