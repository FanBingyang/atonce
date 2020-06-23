package com.byfan.photos.service.Impl;

import com.byfan.photos.entity.NoteFolder;
import com.byfan.photos.jpa.NoteFolderJpa;
import com.byfan.photos.service.NoteFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: FBY
 * @Date: 2020/5/1 19:40
 * @Version 1.0
 */
@Service
public class NoteFolderServiceImpl implements NoteFolderService {

    @Autowired
    private NoteFolderJpa noteFolderJpa;

    /**
     * 创建/修改文件夹
     * @param noteFolder
     * @return
     */
    @Override
    public NoteFolder save(NoteFolder noteFolder) {
        return noteFolderJpa.save(noteFolder);
    }

    /**
     * 根据id删除文件夹
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        noteFolderJpa.deleteById(id);
    }

    /**
     * 根据id查询文件夹
     * @param id
     * @return
     */
    @Override
    public NoteFolder findById(Integer id) {
        Optional<NoteFolder> optional = noteFolderJpa.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 查询所有文件夹
     * @param noteFolder
     * @return
     */
    @Override
    public List<NoteFolder> findAll(NoteFolder noteFolder) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("openId",match -> match.exact())     //"openId"精确匹配
                .withIgnoreNullValues();//忽略空字段
        Example<NoteFolder> example = Example.of(noteFolder,matcher);
        return noteFolderJpa.findAll(example);
    }
}
