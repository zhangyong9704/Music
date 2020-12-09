package com.cloud.music.controller.login;

import com.cloud.music.entity.Admin;
import com.cloud.music.service.AdminService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "后台登录")
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登录状态判断")
    @PostMapping("/login/status")
    public ReturnUnifiedCode loginStatus(@ApiParam(name = "Admin",value = "用户对象",required = true) Admin admin){
        if (null == admin){
            System.out.println("用户信息未传入");
            return ReturnUnifiedCode.errorState().code(2001).message("未接收到用户信息");
        }

        boolean isEnableLogin = adminService.getInfoByAccount(admin);

        return isEnableLogin? ReturnUnifiedCode.successState():ReturnUnifiedCode.errorState();
    }

}
