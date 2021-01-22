package com.cloud.music.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Consumer;
import com.cloud.music.entity.vo.ConsumerQueryVo;
import com.cloud.music.service.ConsumerService;
import com.cloud.music.utils.ReturnStatusCode;
import com.cloud.music.utils.ReturnUnifiedCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static com.cloud.music.utils.ReturnStatusCode.ERROR_STATUS;

/**
 * <p>
 * 前端用户 前端控制器
 * </p>
 *
 * @author zy
 * @since 2021-01-22
 */
@Api(tags =  "ConsumerController")
@RestController
@RequestMapping("/consumer")
@CrossOrigin
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    /**
     * 方法说明
     * @Title: 带条件的的用户信息分页查询
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 14:37
     */
    @ApiOperation("条件的的用户信息分页查询")
    @PostMapping("/queryConsumers/{currentPage}/{limitSize}")
    public ReturnUnifiedCode selectConsumerPages(@RequestBody(required = false) ConsumerQueryVo consumerQueryVo,
                                               @PathVariable long currentPage,
                                               @PathVariable long limitSize){

        Page<Consumer> consumerPage = new Page<>(currentPage,limitSize);  //创建page对象

        Map<String,Object> consumerList =  consumerService.getConsumerPages(consumerPage,consumerQueryVo)  ;

        return consumerList.size()>0?ReturnUnifiedCode.successState().data("data",consumerList.get("Consumer")).data("total",consumerList.get("total")):
                ReturnUnifiedCode.errorState().message("获取分页数据异常");
    }

    /**
     * 方法说明
     * @Title: 无条件查询所有用户信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 15:21
     */
    @ApiOperation("无条件查询所有用户信息")
    @GetMapping("/queryConsumers")
    public ReturnUnifiedCode queryAllConsumer(){

        List<Consumer> consumerList = consumerService.getAllConsumers();

        return ReturnUnifiedCode.successState().data("consumer",consumerList);

    }

    /**
     * 方法说明
     * @Title: 根据id查询用户
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:22
     */
    @ApiOperation("根据id查询用户")
    @GetMapping("/queryConsumer/{id}")
    public ReturnUnifiedCode queryConsumerById(@PathVariable("id") Integer id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法查找");
        }
        Consumer consumerOne = consumerService.getConsumerOneById(id);
        return ReturnUnifiedCode.successState().data("consumer",consumerOne);
    }

    /**
     * 方法说明
     * @Title: 根据ID 删除用户
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:13
     */
    @ApiOperation("根据ID删除用户")
    @DeleteMapping("/deleteConsumer/{id}")
    public ReturnUnifiedCode deleteConsumerById(@PathVariable("id") String id){
        if (null == id){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前id为空,无法删除");
        }
        boolean state = consumerService.deleteConsumer(id);
        return state?ReturnUnifiedCode.successState().message("删除成功"):
                ReturnUnifiedCode.errorState().message("数据异常");
    }

    /**
     * 方法说明
     * @Title: 根据id批量删除用户
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:14
     */
    @ApiOperation("根据id批量删除用户")
    @DeleteMapping("/deleteBatch/{params}")
    public ReturnUnifiedCode deleteBatchConsumerByIds(@PathVariable String[] params){
        if (params.length<=0){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"删除ids为空,无法删除");
        }
        boolean deleteState = consumerService.deleteBatchConsumerByIds(params);
        return deleteState?ReturnUnifiedCode.successState().message("批量删除成功"):
                ReturnUnifiedCode.errorState().message("批量删除失败");
    }

    /**
     * 方法说明
     * @Title: 根据id 修改用户信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 16:56
     */
    @ApiOperation("修改Consumer信息")
    @PutMapping("/update")
    public ReturnUnifiedCode updateSingeById(@RequestBody Consumer consumer){
        if (null == consumer){
            throw new MusicExceptionMessage(ReturnStatusCode.ERROR_STATUS,"当前对象为空,修改失败");
        }
        boolean update = consumerService.updateConsumer(consumer);
        return update?ReturnUnifiedCode.successState().message("修改成功"):ReturnUnifiedCode.errorState().message("修改失败");
    }

    /**
     * 方法说明
     * @Title: 添加用户信息
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation("添加用户信息")
    @PostMapping("/add")
    public ReturnUnifiedCode insertConsumerInfo(@RequestBody Consumer consumer){
        boolean insert = consumerService.insertConsumerOne(consumer);
        return insert?ReturnUnifiedCode.successState().message("添加成功"):ReturnUnifiedCode.errorState().message("添加失败");
    }

    /**
     * 方法说明
     * @Title: 上传用户封面
     * @Description TODO
     * @Param
     * @return
     * @date 2020-12-11 -- 17:16
     */
    @ApiOperation(value = "上传用户封面")
    @PostMapping(value = "/upload-cover", consumes = "multipart/*",  headers = "content-type=multipart/form-data")
    public ReturnUnifiedCode uploadConsumerCover(MultipartFile file){
        if(file.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"上传文件为空");
        }
        String uploadPath = consumerService.uploadConsumerFileOne(file);
        return null!=uploadPath?ReturnUnifiedCode.successState().data("path",uploadPath):
                ReturnUnifiedCode.errorState().message("文件上传失败");
    }

    @ApiOperation(value = "删除上次上传用户封面")
    @PostMapping("/delete-upload")
    public ReturnUnifiedCode uploadDeletePreviousConsumerCover(@RequestParam("filePath") String filePath){
        if(filePath.isEmpty()){
            throw  new MusicExceptionMessage(ERROR_STATUS,"删除文件地址为空");
        }
        boolean uploadPath = consumerService.deletePreviousConsumerCover(filePath);
        return uploadPath?ReturnUnifiedCode.successState().message("删除文件成功"):
                ReturnUnifiedCode.errorState().message("文件上传失败");
    }


}

