package com.byfan.photos.service;

import com.byfan.photos.entity.Album;
import com.byfan.photos.entity.Photo;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:30
 * @Version 1.0
 */
public interface AlbumService {
    //创建/修改
    Album save(Album album);

    //根据id删除相册
    void deleteById(Integer id);

    //根据id查询相册
    Album findById(Integer id);

    //查找所有相册
    List<Album> findAll(Album album);


}
