package com.byfan.photos.service;

import com.byfan.photos.entity.UserPc;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/3/2 11:16
 * @Version 1.0
 */
public interface UserPcService {

    //注册用户
    UserPc save(UserPc userPc);

    //通过用户id查找
    UserPc findById(Integer id);

    //条件查找
    List<UserPc> findByExample(UserPc userPc);

    //查找所用用户
    List<UserPc> findAll();
}
