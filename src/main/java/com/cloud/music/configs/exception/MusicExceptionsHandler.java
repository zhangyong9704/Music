package com.cloud.music.configs.exception;

import com.cloud.music.utils.ReturnUnifiedCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容摘要:
 * <p>文件名称: MusicExceptions.java
 * <p>版权所有: 版权所有(C)2015-2020
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @Version v1.0
 * @Author Administrator
 * @Date 2020-12-11 -- 16:23
 * @Description TODO
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class MusicExceptionsHandler {

    /**
     * 方法说明
     * @Title: 统一异常返回格式
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:44
    */
    @ExceptionHandler(Exception.class)
    public ReturnUnifiedCode globleExceptionHander(Exception e){
        return ReturnUnifiedCode.errorState().code(e.hashCode()).message(e.getMessage());
    }

    /** 
     * 方法说明
     * @Title: 处理自定义异常返回格式
     * @Description TODO
     * @Param 
     * @return 
     * @date 2020-12-11 -- 16:34
    */
    @ExceptionHandler(MusicExceptionMessage.class) //指定出现什么异常执行这个方法
    public ReturnUnifiedCode musicException(MusicExceptionMessage e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ReturnUnifiedCode.errorState().code(e.getCode()).message(e.getMessage());
    }

}
