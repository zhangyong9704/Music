package com.cloud.music.configs.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 内容摘要:
 * <p>文件名称: MusicExceptionMessage.java
 * <p>版权所有: 版权所有(C)2015-2020
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @Version v1.0
 * @Author Administrator
 * @Date 2020-12-11 -- 16:30
 * @Description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class MusicExceptionMessage extends RuntimeException{

    private Integer code;  //异常码

    private String message;  //异常信息

}
