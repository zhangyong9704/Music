package com.cloud.music.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.music.common.upload.UploadToLocalService;
import com.cloud.music.common.uploadcConstParams.UploadLocalPathConfig;
import com.cloud.music.configs.exception.MusicExceptionMessage;
import com.cloud.music.entity.Consumer;
import com.cloud.music.entity.vo.ConsumerQueryVo;
import com.cloud.music.mapper.ConsumerMapper;
import com.cloud.music.service.ConsumerService;
import com.cloud.music.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static com.cloud.music.utils.ReturnStatusCode.ERROR_STATUS;

/**
 * <p>
 * 前端用户 服务实现类
 * </p>
 *
 * @author zy
 * @since 2021-01-22
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {

    @Autowired
    UploadToLocalService uploadToLocalService;

    @Autowired
    UploadLocalPathConfig uploadLocalPathConfig;

    @Override
    public Map<String, Object> getConsumerPages(Page<Consumer> consumerPage, ConsumerQueryVo consumerQueryVo) {
        QueryWrapper<Consumer>  wrapper =  new QueryWrapper<>(); //构造条件筛选器
        if (!StringUtils.isEmpty(consumerQueryVo.getUsername())){   //根据姓名查询
            wrapper.like("username",consumerQueryVo.getUsername());
        }
        if (null!=consumerQueryVo.getSex()){ //o-女 1-男 根据性别查询
            wrapper.eq("sex",consumerQueryVo.getSex());
        }
        if (!StringUtils.isEmpty(consumerQueryVo.getPhoneNum())){  //根据电话查询
            wrapper.like("phone_num",consumerQueryVo.getPhoneNum());
        }
        if (!StringUtils.isEmpty(consumerQueryVo.getEmail())){  //根据邮箱查询
            wrapper.like("email",consumerQueryVo.getEmail());
        }
        if (!StringUtils.isEmpty(consumerQueryVo.getLocation())){  //根据地区查询
            wrapper.like("location",consumerQueryVo.getLocation());
        }
        wrapper.orderByAsc("id");  //根据id排序

        Page<Consumer> page = this.page(consumerPage, wrapper);

        Map<String,Object> result = new HashMap<>();
        result.put("Consumer",page.getRecords()); //获取集合
        result.put("total", page.getTotal());  //获取记录总数

        return result;
    }

    @Override
    public List<Consumer> getAllConsumers() {
        return this.list();
    }

    @Override
    public Consumer getConsumerOneById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean deleteConsumer(String id) {
        boolean deleteFile = uploadToLocalService.deleteFile(this.getById(id).getAvator()); //获得图片地址并删除
        return deleteFile && this.removeById(id);
    }

    @Override
    public boolean deleteBatchConsumerByIds(String[] params) {
        List<String> deleteParams = new ArrayList<>();
        for (int i = params.length - 1; i >= 0; i--) {
            deleteParams.add(params[i]);
        }
        //获取图片地址
        List<String> collect = baseMapper.selectBatchIds(deleteParams).stream().map(Consumer::getAvator).collect(Collectors.toList());
        boolean batchFiles = uploadToLocalService.deleteBatchFiles(collect);  //批量删除图片
        return batchFiles && this.removeByIds(deleteParams);
    }

    @Override
    public boolean updateConsumer(Consumer consumer) {
        return this.updateById(consumer);
    }

    @Override
    public boolean insertConsumerOne(Consumer consumer) {
        //查询当前注册的账户是否已存在
        Consumer consumerOne = this.getOne(new QueryWrapper<Consumer>().eq("username", consumer.getUsername()));
        if (consumerOne!=null){
            throw new MusicExceptionMessage(ERROR_STATUS,"当前账户已存在,请进行登录");
        }
        String salt = UUID.randomUUID().toString().replaceAll("-","").substring(8);   //随机盐值
        PasswordEncryptor encoderMd5 = new PasswordEncryptor(salt, "SHA-256");
        String encodedPassword = encoderMd5.encode(consumer.getPassword());//加盐后的MD5密码
        consumer.setPassword(encodedPassword);
        consumer.setSalt(salt);
        return this.save(consumer);
    }

    @Override
    public String uploadConsumerFileOne(MultipartFile file) {
        String consumerCoverPath = uploadToLocalService.uploadFileOne(file, UploadLocalPathConfig.userCoverPath, "USERS");
        return null==consumerCoverPath?"":consumerCoverPath;
    }

    @Override
    public boolean deletePreviousConsumerCover(String filePath) {
        return uploadToLocalService.deleteFile(filePath);
    }

    /*===================================================================================*/

    @Override
    public Consumer verifyConsumerLogin(Consumer consumer) {
        Consumer consumerOne = this.getOne(new QueryWrapper<Consumer>().eq("username", consumer.getUsername()));
        if (consumerOne==null){
            throw new MusicExceptionMessage(ERROR_STATUS,"当前账户不存在");
        }
        PasswordEncryptor encoderMd5 = new PasswordEncryptor(consumerOne.getSalt(), "SHA-256");
        String consumerPassword = encoderMd5.encode(consumer.getPassword());//加密当前用户信息
        if ((consumerOne.getPassword()).equalsIgnoreCase(consumerPassword)){   //比对当前用户密码
            return consumerOne;
        }else{
            return null;
        }
    }
}
