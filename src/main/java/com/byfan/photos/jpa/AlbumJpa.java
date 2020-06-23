package com.byfan.photos.jpa;

import com.byfan.photos.entity.Album;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:34
 * @Version 1.0
 */

/**
 * 相册的持久层
 */
public interface AlbumJpa extends BaseRepository<Album,Integer>{
}
