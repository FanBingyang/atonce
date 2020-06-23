package com.byfan.photos.jpa;

import com.byfan.photos.entity.Photo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:38
 * @Version 1.0
 */

/**
 * 照片持久层
 */
public interface PhotoJpa extends BaseRepository<Photo,Integer>{

    /**
     * 在@Query 注解里设置value ，?1、?2 分别代表第一第二个参数，
     * @Query只能用于查询，如果想用该注解实现其他操作类型就需要配合@Modifying注解一起使用，
     * 但是只是这么写的话会抛出一个TranscationRequiredException异常，意思就是当前操作需要开启事务，
     * 所以需要在这个前加上@Transactional注解开启自动化管理事务。
     *
     * 根据相册分页查询照片数据
     * @param albumId 相册id
     * @param start 开始位置  （页面-1）×每页数量
     * @param size  每页数量
     * @return
     */
    // @Transactional
    // @Modifying
    @Query(value = "SELECT * FROM photo WHERE album_id=?1 AND status=1 ORDER BY creat_time DESC LIMIT ?2,?3",nativeQuery = true)
    List<Photo> selectPage(Integer albumId, Integer start,Integer size);


    /**
     * 根据用户openid分页查询回收站照片
     * @param openId
     * @param start
     * @param size
     * @return
     */
    @Query(value = "SELECT * from photo where openid=?1 and status=0 order by last_time desc LIMIT ?2,?3",nativeQuery = true)
    List<Photo> selectTrash(String openId,Integer start,Integer size);

}
