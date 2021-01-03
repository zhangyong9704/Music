package com.cloud.music.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.music.entity.Banner;
import com.cloud.music.entity.vo.BannerQueryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author zy
 * @since 2020-08-19
 */
public interface BannerService extends IService<Banner> {

    /**
     * 方法说明
     * @Title: 获取Banner分页列表
     * @Description TODO
     * @Param
     * @return
     * @date 2021-1-02 -- 17:14
     */
    Map<String, Object> pageBanner(Page<Banner> pageParam, BannerQueryVo bannerQueryVo);

    /**
     * 方法说明
     * @Title: 根据ID获取Banner
     * @Description TODO
     * @Param
     * @return
     * @date 2021-1-02 -- 17:14
     */
    Banner getBannerById(String id);

    /**
     * 方法说明
     * @Title: 新增Banner
     * @Description TODO
     * @Param
     * @return
     * @date 2021-1-02 -- 17:14
     */
    boolean saveBanners(Banner banner);

    /**
     * 方法说明
     * @Title: 根据id修改Banner
     * @Description TODO
     * @Param
     * @return
     * @date 2021-1-02 -- 17:14
     */
    boolean updateBannerById(Banner banner);

    /**
     * 方法说明
     * @Title: 删除Banner
     * @Description TODO
     * @Param
     * @return
     * @date 2021-1-02 -- 17:14
     */
    boolean removeBanner(String id);

    /**
     * 方法说明
     * @Title: 获取首页banner
     * @Description TODO
     * @Param
     * @return
     * @date 2021-1-02 -- 17:14
     */
    List<Banner> selectIndexList();

    /**
     * 方法说明
     * @Title: 根据id批量删除Banner
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:14
     */
    boolean deleteBatchBannerByIds(String[] params);

    /**
     * 方法说明
     * @Title: 上传Banner封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    String uploadBannerFileOne(MultipartFile file);

    /**
     * 方法说明
     * @Title: 删除上次封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    boolean deletePreviousBannerCover(String filePath);
}
