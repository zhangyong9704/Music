package com.cloud.music.utils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName ReturnUnifiedResultsCode.java
 * @Description  返回统一结果编码集
 * @CreateDate 2020-12-01  15:54:29
 * 定义统一结果形式:
 * {
 *   "success": 布尔, //响应是否成功
 *   "code": 数字, //响应码
 *   "message": 字符串, //返回消息
 *   "data": HashMap //返回数据，放在键值对中
 * }
 */

@Data
public class ReturnUnifiedCode {
    //私有化构造方法
    private ReturnUnifiedCode(){};

    //是否成功
    private Boolean success;

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * 统一返回成功信息格式
     * @return
     */
    public static ReturnUnifiedCode successState(){
        ReturnUnifiedCode resultsCode = new ReturnUnifiedCode();
        resultsCode.setSuccess(true);
        resultsCode.setCode(ReturnStatusCode.SUCCESS_STATUS);
        resultsCode.setMessage(ReturnStatusCode.SUCCESS_MESSAGE);
        return  resultsCode;
    }

    /**
     * 统一返回失败信息格式
     * @return
     */
    public static ReturnUnifiedCode errorState(){
        ReturnUnifiedCode resultsCode = new ReturnUnifiedCode();
        resultsCode.setSuccess(false);
        resultsCode.setCode(ReturnStatusCode.ERROR_STATUS);
        resultsCode.setMessage(ReturnStatusCode.ERROR_MESSAGE);
        return  resultsCode;
    }

    /**
     * 设置链式方法
     * @param success
     * @return
     */
    public ReturnUnifiedCode success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ReturnUnifiedCode message(String message){
        this.setMessage(message);
        return  this;
    }

    public ReturnUnifiedCode code(Integer code){
        this.setCode(code);
        return this;
    }

    public ReturnUnifiedCode data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public ReturnUnifiedCode data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
}
