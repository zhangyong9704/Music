package com.cloud.music.utils;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName ReturnStatusCode.java
 * @Description  定义返回状态编码常量
 * @CreateDate 2020-12-01  15:57:19
 */

public interface ReturnStatusCode {

    /**
     * 返回成功状态码
     */
    Integer SUCCESS_STATUS = 200 ;

    /**
     * 返回成功信息
     */
    String SUCCESS_MESSAGE = "成功" ;


    /**
     * 返回失败状态码
     */
    Integer ERROR_STATUS = 201 ;

    /**
     * 返回失败信息
     */
    String ERROR_MESSAGE = "失败" ;
}
