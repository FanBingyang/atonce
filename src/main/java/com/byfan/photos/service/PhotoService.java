package com.byfan.photos.service;


import com.byfan.photos.entity.Photo;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:31
 * @Version 1.0
 */
public interface PhotoService {


    //保存或修改照片信息
    Photo save(Photo photo);

    //硬删除
    void deleteById(Integer id);

    //通过id查找
    Photo findById(Integer id);

    //照片分类
    List<Photo> findByExample(Photo photo);

    // 根据相册id分页查询照片数据
    List<Photo> selectPage(Integer albumId,Integer page,Integer size);

    // 根据用户openid分页查询照片
    List<Photo> selectTrash(String openId,Integer page,Integer size);


}
