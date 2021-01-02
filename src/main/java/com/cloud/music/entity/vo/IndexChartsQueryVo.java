package com.cloud.music.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zy
 * @version 1.0.0
 * @ClassName IndexChartsQueryVo.java
 * @Description 后端主页图标返回格式
 * @CreateDate 2021-01-02  15:04:00
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="IndexChartsQueryVo对象", description="后端主页图标返回格式对象")
public class IndexChartsQueryVo implements Serializable {

    private String[] columns;

    private List<Map<String,Object>> rows;
}
