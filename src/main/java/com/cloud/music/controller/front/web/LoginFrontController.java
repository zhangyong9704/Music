package com.cloud.music.controller.front.web;


import com.cloud.music.entity.Consumer;
import com.cloud.music.service.ConsumerService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName IndexFrontController.java
 * @Description 前台登录控制器
 * @CreateDate 2021-01-06  22:11:12
 */

@RestController
@RequestMapping("/login-front")
@Api(tags = "LoginFrontController")
@CrossOrigin //跨域
public class LoginFrontController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 方法说明
     * @Title: 前台用户登录控制器
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "前台用户登录控制器")
    @PostMapping("/consumer-login")
    public ReturnUnifiedCode frontConsumerLogin(@RequestBody Consumer consumer) {
        if (consumer == null ){
            return ReturnUnifiedCode.errorState().message("未获取登录信息,请重新登录").data("data",null);
        }
        if (consumer.getUsername()==null || consumer.getUsername().equals("")){
            return ReturnUnifiedCode.errorState().message("请输入登录信息").data("data",null);
        }
        if (consumer.getPassword()==null || consumer.getPassword().equals("")){
            return ReturnUnifiedCode.errorState().message("请输入密码信息").data("data",null);
        }
        Consumer verifyResult = consumerService.verifyConsumerLogin(consumer);

        return (null != verifyResult)?ReturnUnifiedCode.successState().data("data",verifyResult):
                ReturnUnifiedCode.errorState().message("用户验证信息失败").data("data",null);
    }
}
