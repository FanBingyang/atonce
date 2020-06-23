package com.byfan.photos.service;

import com.byfan.photos.entity.NoteFolder;

import java.util.List;

/**
 * @Author: FBY
 * @Date: 2020/5/1 19:37
 * @Version 1.0
 */
public interface NoteFolderService {
    // 创建/修改
    NoteFolder save(NoteFolder noteFolder);

    // 根据id删除文件夹
    void deleteById(Integer id);

    //根据id查询文件夹
    NoteFolder findById(Integer id);

    //查找所有文件夹
    List<NoteFolder> findAll(NoteFolder noteFolder);
}
