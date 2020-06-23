package com.byfan.photos.jpa;

import com.byfan.photos.entity.Notepad;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:37
 * @Version 1.0
 */

/**
 * 记事本持久层
 */
public interface NotepadJpa extends BaseRepository<Notepad,Integer> {

    /**
     * 根据用户openid分页查询回收站记事本
     * @param openId
     * @param start
     * @param size
     * @return
     */
    @Query(value = "SELECT * FROM notepad WHERE openid=?1 AND status=0 ORDER BY last_time DESC LIMIT ?2,?3",nativeQuery = true)
    List<Notepad> selectTrash(String openId,Integer start,Integer size);

    /**
     * 根据文件夹id分页查询记事本
     * @param folderId
     * @param start
     * @param size
     * @return
     */
    @Query(value = "SELECT * FROM notepad WHERE folderid=?1 AND status=1 ORDER BY creat_time DESC LIMIT ?2,?3",nativeQuery = true)
    List<Notepad> selectPage(Integer folderId,Integer start,Integer size);


    /**
     * 根据用户id分页查询所有记事本
     * @param openId
     * @param start
     * @param size
     * @return
     */
    @Query(value = "SELECT * FROM notepad WHERE openid=?1 AND status=1 ORDER BY creat_time DESC LIMIT ?2,?3",nativeQuery = true)
    List<Notepad> selectPageAll(String openId,Integer start,Integer size);
}
