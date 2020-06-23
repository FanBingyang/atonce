package com.byfan.photos.service;

import com.byfan.photos.entity.Notepad;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:31
 * @Version 1.0
 */
public interface NotepadService {
    //保存/修改/软删除
    Notepad save(Notepad notepad);

    //根据id删除
    void deleteById(Integer id);

    //根据id查询
    Notepad findById(Integer id);

    //根据条件查找
    List<Notepad> findByExample(Notepad notepad);

    // 根据用户openid分页查询回收站记事本
    List<Notepad> selectTrash(String openId,Integer page,Integer size);

    // 根据文件夹id分页查询记事本
    List<Notepad> selectPage(Integer folderId,Integer page,Integer size);

    // 根据用户openid分页查询所有记事本
    List<Notepad> selectPageAll(String openId,Integer page,Integer size);
}
