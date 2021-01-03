package com.cloud.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.entity.Banner;
import com.cloud.music.entity.vo.BannerQueryVo;
import com.cloud.music.mapper.BannerMapper;
import com.cloud.music.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author zy
 * @since 2020-08-19
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Autowired
    UploadToLocalService uploadToLocalService;

    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;


    @Override

    public Map<String, Object> pageBanner(Page<Banner> pageParam, BannerQueryVo bannerQueryVo) {

        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(bannerQueryVo.getTitle())){ //根据名称查询
            queryWrapper.like("title", bannerQueryVo.getTitle());  //模糊查询
        }
        if (!StringUtils.isEmpty(bannerQueryVo.getBegin())){
            queryWrapper.ge("create_time", bannerQueryVo.getBegin());
        }
        if (!StringUtils.isEmpty(bannerQueryVo.getEnd())){
            queryWrapper.le("create_time", bannerQueryVo.getEnd());
        }
        queryWrapper.orderByDesc("sort");
        queryWrapper.orderByDesc("create_time");  //默认按照创建日期降序排列(新增显示在前)

        Page<Banner> page = this.page(pageParam, queryWrapper);
        Map<String,Object> result = new HashMap<>();
        result.put("Banner",page.getRecords()); //获取集合
        result.put("total", page.getTotal());  //获取记录总数

        return result;
    }

    @Override
    public Banner getBannerById(String id) {
        return this.getById(id);
    }

    @Override
    public boolean saveBanners(Banner banner) {
        return this.save(banner);
    }

    @Override
    public boolean updateBannerById(Banner banner) {
        return this.updateById(banner);
    }

    @Override
    public boolean removeBanner(String id) {
        boolean deleteFile = uploadToLocalService.deleteFile(this.getById(id).getImageUrl()); //获得图片地址并删除
        return deleteFile && this.removeById(id);
    }


    //@Cacheable(value = "banner", key = "'selectIndexList'")  //开启缓存
    @Override
    public List<Banner> selectIndexList() {

        QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");  //默认按照创建日期降序排列(新增显示在前)
        queryWrapper.last("limit 2");  //默认查询前两条数据

        return this.list(queryWrapper);
    }

    @Override
    public boolean deleteBatchBannerByIds(String[] params) {
        List<String> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        //获取图片地址
        List<String> collect = baseMapper.selectBatchIds(deleteParams).stream().map(Banner::getImageUrl).collect(Collectors.toList());
        boolean batchFiles = uploadToLocalService.deleteBatchFiles(collect);  //批量删除图片
        return batchFiles && this.removeByIds(deleteParams);
    }

    @Override
    public String uploadBannerFileOne(MultipartFile file) {
        String bannerCoverPath = uploadToLocalService.uploadFileOne(file, UploadLocalPathConfig.bannerCoverPath, "BANNER");
        return null==bannerCoverPath?"":bannerCoverPath;
    }

    @Override
    public boolean deletePreviousBannerCover(String filePath) {
        return uploadToLocalService.deleteFile(filePath);
    }


}
