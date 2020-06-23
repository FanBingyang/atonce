package com.byfan.photos.service.Impl;

import com.byfan.photos.entity.Notepad;
import com.byfan.photos.jpa.NotepadJpa;
import com.byfan.photos.service.NotepadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: FBY
 * @Date: 2020/2/26 22:46
 * @Version 1.0
 */
@Service
public class NotepadServiceImpl implements NotepadService {

    @Autowired
    private NotepadJpa notepadJpa;

    /**
     * 保存/修改/软删除记事本
     * @param notepad
     * @return
     */
    @Override
    public Notepad save(Notepad notepad) {
        return notepadJpa.save(notepad);
    }

    /**
     * 根据id删除记事本
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        notepadJpa.deleteById(id);
    }

    /**
     * 根据id查找记事本
     * @param id
     * @return
     */
    @Override
    public Notepad findById(Integer id) {
        Optional<Notepad> optional = notepadJpa.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    /**
     * 根据条件查找记事本
     * @param notepad
     * @return
     */
    @Override
    public List<Notepad> findByExample(Notepad notepad) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("openId",match -> match.exact())     //"openId"精确匹配
                .withMatcher("title",match -> match.contains())     //"标题"模糊匹配
                .withMatcher("text",match -> match.contains())     //"文本"模糊匹配
                .withMatcher("creatTime",match -> match.contains())     //"时间"模糊匹配
                .withMatcher("status",match -> match.exact())     //"状态"精确匹配
                .withMatcher("classify",match -> match.exact())      //"分类"精准匹配
                .withMatcher("folderId",match -> match.exact())
                .withIgnoreNullValues();//忽略空字段
        Example<Notepad> example = Example.of(notepad,matcher);
        Sort sort = Sort.by(Sort.Direction.DESC,"creatTime");
        return notepadJpa.findAll(example,sort);
    }

    /**
     * 根据用户openid分页查询回收站记事本
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Notepad> selectTrash(String openId, Integer page, Integer size) {
        return notepadJpa.selectTrash(openId,(page-1)*size,size);
    }

    /**
     * 根据文件夹id分页查询记事本
     * @param folderId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Notepad> selectPage(Integer folderId, Integer page, Integer size) {
        return notepadJpa.selectPage(folderId,(page-1)*size,size);
    }

    /**
     * 根据用户openid分页查询所有记事本
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Notepad> selectPageAll(String openId, Integer page, Integer size) {
        return notepadJpa.selectPageAll(openId,(page-1)*size,size);
    }
}
