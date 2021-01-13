package com.cloud.music.controller.front.web;

import com.cloud.music.entity.Banner;
import com.cloud.music.service.BannerService;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 banner前台查询接口
 * </p>
 * @author zy
 * @since 2020-08-19
 */


@RestController
@RequestMapping("/banner-front")
@Api(tags = "BannerFrontController")
@CrossOrigin //跨域
public class BannerFrontController {

	@Autowired
	private BannerService bannerService;

	/**
	 * 方法说明
	 * @Title: 获取首页banner
	 * @Description TODO
	 * @return
	 * @date 2020-1-02 -- 16:37
	 */
	@ApiOperation(value = "获取首页banner")
	@GetMapping("/banners")
	public ReturnUnifiedCode indexBanner(@RequestParam Integer size) {

		List<Banner> bannerList = bannerService.selectIndexList(null==size?5:size);

		return (null != bannerList)?ReturnUnifiedCode.successState().data("data",bannerList):
				ReturnUnifiedCode.errorState().message("数据异常，获取banner不存在").data("data",null);
	}
}