package com.cloud.music.service;

import com.cloud.music.entity.Admin;

/**
 * 内容摘要：
 * <p>文件名称: AdminService.java
 * <p>公　　司: 北京赛福阔利特科技有限公司
 * <p>版权所有: 版权所有(C)2015-2020
 * <p>修改记录: ...</li>
 * <p>其他说明: ...</li>
 *
 * @author ZhangYong
 * @version v1.0
 * @date 2020-11-25  16:28
 */

public interface AdminService {


    boolean getInfoByAccount(Admin admin);

}