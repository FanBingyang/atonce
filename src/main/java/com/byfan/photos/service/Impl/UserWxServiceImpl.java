package com.byfan.photos.service.Impl;

import com.byfan.photos.entity.UserWx;
import com.byfan.photos.jpa.UserWxJpa;
import com.byfan.photos.service.UserWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: FBY
 * @Date: 2020/2/26 22:02
 * @Version 1.0
 */
@Service
public class UserWxServiceImpl implements UserWxService {

    @Autowired
    private UserWxJpa userJpa;


    /**
     * 添加或修改用户信息
     * @param user
     * @return
     */
    @Override
    public UserWx save(UserWx user) {
        return userJpa.save(user);
    }

    /**
     * 根据用户id查询信息
     * @param openid
     * @return
     */
    @Override
    public UserWx findById(String openid) {
        Optional<UserWx> optional = userJpa.findById(openid);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
