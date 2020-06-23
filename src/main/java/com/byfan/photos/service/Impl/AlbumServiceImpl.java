package com.byfan.photos.service.Impl;

import com.byfan.photos.entity.Album;
import com.byfan.photos.entity.Photo;
import com.byfan.photos.jpa.AlbumJpa;
import com.byfan.photos.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: FBY
 * @Date: 2020/2/27 12:18
 * @Version 1.0
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumJpa albumJpa;

    /**
     * 创建/修改
     * @param album
     * @return
     */
    @Override
    public Album save(Album album) {
        return albumJpa.save(album);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        albumJpa.deleteById(id);
    }

    @Override
    public Album findById(Integer id) {
        Optional<Album> optional = albumJpa.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 查找所有相册
     * @param album
     * @return
     */
    @Override
    public List<Album> findAll(Album album) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher("openId",match -> match.exact());
        Example example = Example.of(album,matcher);
        return albumJpa.findAll(example);
    }


}
