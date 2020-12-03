package com.cloud.music.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 内容摘要：
 * <p>文件名称: Admin.java
 * <p>版权所有: 版权所有(C)2015-2020
 *
 * @author ZhangYong
 * @version v1.0
 * @date 2020-11-25  17:41
 */
@Data
@ApiModel(value = "用户登录对象",description = "后台登录接收对象")
public class Admin {

    @ApiModelProperty(value = "id")
    private int id ;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;
}
