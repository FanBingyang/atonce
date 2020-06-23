package com.byfan.photos.service.Impl;

import com.byfan.photos.entity.UserPc;
import com.byfan.photos.jpa.UserPcJpa;
import com.byfan.photos.service.UserPcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: FBY
 * @Date: 2020/3/2 11:23
 * @Version 1.0
 */
@Service
public class UserPcServiceImpl implements UserPcService {
    @Autowired
    private UserPcJpa userPcJpa;

    /**
     * 保存用户信息
     * @param userPc
     * @return
     */
    @Override
    public UserPc save(UserPc userPc) {
        return userPcJpa.save(userPc);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Override
    public UserPc findById(Integer id) {
        Optional<UserPc> optional = userPcJpa.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 条件查找
     * @param userPc
     * @return
     */
    @Override
    public List<UserPc> findByExample(UserPc userPc) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username",match -> match.exact())
                .withMatcher("password",match -> match.exact())
                .withMatcher("openId",match -> match.exact())
                .withIgnoreNullValues();
        Example<UserPc> example = Example.of(userPc,matcher);
        return userPcJpa.findAll(example);
    }

    /**
     * 查找所用用户
     * @return
     */
    @Override
    public List<UserPc> findAll() {
        return userPcJpa.findAll();
    }
}
