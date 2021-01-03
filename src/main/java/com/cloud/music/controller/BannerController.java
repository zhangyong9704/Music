package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Banner;
import com.cloud.music.entity.vo.BannerQueryVo;
import com.cloud.music.service.BannerService;
import com.cloud.music.utils.ReturnStatusCode;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.cloud.music.utils.ReturnStatusCode.ERROR_STATUS;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 * @author zy
 * @since 2021-1-02
 */

@Api(tags =  "BannerController")
@RestController
@RequestMapping("/banner")
@CrossOrigin
public class BannerController {

    @Autowired
    private BannerService bannerService;


    /**
     * 方法说明
     * @Title: 获取Banner分页列表
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "获取Banner分页列表")
    @PostMapping("queryBanner/{currentPage}/{limitSize}")
    public ReturnUnifiedCode selectIndexBanners( @PathVariable long currentPage,
                                                 @PathVariable long limitSize,
                                                @RequestBody(required = false) BannerQueryVo bannerQueryVo) {

        Page<Banner> page = new Page<>(currentPage, limitSize);
        Map<String,Object> bannerList = bannerService.pageBanner(page, bannerQueryVo);

        return bannerList.size()>0?ReturnUnifiedCode.successState().data("total",bannerList.get("total")).data("banner",bannerList.get("Banner")):
               ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }


    /**
     * 方法说明
     * @Title: 根据ID获取Banner
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "根据ID获取Banner")
    @GetMapping("/get/{id}")
    public ReturnUnifiedCode getBannersById(@PathVariable String id) {

        Banner banner = bannerService.getBannerById(id);

        return (null != banner)?ReturnUnifiedCode.successState().data("data",banner):
                ReturnUnifiedCode.errorState().message("数据不存在").data("data",null);
    }


    /**
     * 方法说明
     * @Title: 新增Banner
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "新增Banner")
    @PostMapping("/add")
    public ReturnUnifiedCode saveBanners(
            @ApiParam(name = "Banner", value = "banner对象", required = true)
            @RequestBody Banner banner) {

        boolean save = bannerService.saveBanners(banner);

        return save?ReturnUnifiedCode.successState().message("新增Banner成功"):
                ReturnUnifiedCode.errorState().message("数据异常,新增Banner失败");
    }


    /**
     * 方法说明
     * @Title: 根据id修改Banner
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "根据id修改Banner")
    @PutMapping("/update")
    public ReturnUnifiedCode updateBannersById(@ApiParam(name = "banner", value = "banner对象", required = true)
            @RequestBody Banner banner) {
        boolean updateState = false;
        try {
            updateState = bannerService.updateBannerById(banner);
        }catch (MusicExceptionMessage musicExceptionMessage){
            throw new MusicExceptionMessage(20002,"修改banner失败，数据异常");  //自定义类异常
        }catch (Exception e){
            //此处会执行GlobalBaseException中的全局异常类
        }

        return updateState?ReturnUnifiedCode.successState().message("修改Banner成功"):
                ReturnUnifiedCode.errorState().message("数据异常,修改Banner失败");
    }



    /**
     * 方法说明
     * @Title: 删除Banner
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("/deleteBanner/{id}")
    public ReturnUnifiedCode removeBannersById(
            @ApiParam(name = "id", value = "bannerID", required = true)   //swagger作用于变量的注释
            @PathVariable String id) {

        boolean remove = bannerService.removeBanner(id);

        return remove?ReturnUnifiedCode.successState():ReturnUnifiedCode.errorState();
    }


    /**
     * 方法说明
     * @Title: 根据id批量删除Banner
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:14
     */
    @ApiOperation("根据id批量删除Banner")
    @DeleteMapping("/deleteBatch/{params}")
    public ReturnUnifiedCode deleteBatchSingerByIds(@PathVariable String[] params){
        if (params.length<=0){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"删除ids为空,无法删除");
        }
        boolean deleteState = bannerService.deleteBatchBannerByIds(params);
        return deleteState?ReturnUnifiedCode.successState().message("批量删除成功"):
                ReturnUnifiedCode.errorState().message("批量删除失败");
    }


    /**
     * 方法说明
     * @Title: 上传Banner封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "上传Banner封面")
    @PostMapping(value = "/upload-cover", consumes = "multipart/*",  headers = "content-type=multipart/form-data")
    public ReturnUnifiedCode uploadBannerCover(MultipartFile file){
        if(file.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传文件为空");
        }
        String uploadPath = bannerService.uploadBannerFileOne(file);
        return null!=uploadPath?ReturnUnifiedCode.successState().message("Banner上传成功").data("path",uploadPath):
                ReturnUnifiedCode.errorState().message("Banner上传失败");
    }


    /**
     * 方法说明
     * @Title: 删除上次封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "删除上次上传歌手封面")
    @PostMapping("/delete-upload")
    public ReturnUnifiedCode uploadDeletePreviousSingerCover(@RequestParam("filePath") String filePath){
        if(filePath.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"删除文件地址为空");
        }
        boolean uploadPath = bannerService.deletePreviousBannerCover(filePath);
        return uploadPath?ReturnUnifiedCode.successState().message("删除Banner成功"):
                ReturnUnifiedCode.errorState().message("Banner上传失败");
    }


}

