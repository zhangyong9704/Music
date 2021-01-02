package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Banner;
import com.cloud.music.entity.vo.BannerQueryVo;
import com.cloud.music.service.BannerService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/save")
    public ReturnUnifiedCode saveBanners(
            @ApiParam(name = "Banner", value = "banner对象", required = true)
            @RequestBody Banner banner) {

        boolean save = bannerService.saveBanners(banner);

        return save?ReturnUnifiedCode.successState():ReturnUnifiedCode.errorState();
    }


    /**
     * 方法说明
     * @Title: 根据id修改Banner
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "根据id修改Banner")
    @PutMapping("/update/{id}")
    public ReturnUnifiedCode updateBannersById(
            @ApiParam(name = "id", value = "bannerID", required = true)
            @PathVariable String id ,
            @ApiParam(name = "banner", value = "banner对象", required = true)
            @RequestBody Banner banner) {
        boolean updateState = false;
        try {
            banner.setId(id);  //RequestBody需要与postMapping结合使用，不然只能手动set id值  不然teacher接收时会有问题
            updateState = bannerService.updateBannerById(banner);
        }catch (MusicExceptionMessage musicExceptionMessage){
            throw new MusicExceptionMessage(20002,"修改banner失败，数据异常");  //自定义类异常
        }catch (Exception e){
            //此处会执行GlobalBaseException中的全局异常类
        }

        return updateState?ReturnUnifiedCode.successState():ReturnUnifiedCode.errorState();
    }



    /**
     * 方法说明
     * @Title: 删除Banner
     * @Description TODO
     * @return
     * @date 2020-1-02 -- 16:37
     */
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("/remove/{id}")
    public ReturnUnifiedCode removeBannersById(
            @ApiParam(name = "id", value = "bannerID", required = true)   //swagger作用于变量的注释
            @PathVariable String id) {

        boolean remove = bannerService.removeBannerById(id);

        return remove?ReturnUnifiedCode.successState():ReturnUnifiedCode.errorState();
    }


}

