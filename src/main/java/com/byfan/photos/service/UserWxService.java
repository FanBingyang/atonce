package com.byfan.photos.service;

import com.byfan.photos.entity.UserWx;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:31
 * @Version 1.0
 */
public interface UserWxService {

    //保存修改用户信息
    UserWx save(UserWx user);

    //根据id查找用户信息
    UserWx findById(String openid);
}
