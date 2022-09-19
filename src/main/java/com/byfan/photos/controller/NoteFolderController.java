package com.byfan.photos.controller;

import com.byfan.photos.entity.NoteFolder;
import com.byfan.photos.entity.Notepad;
import com.byfan.photos.service.NoteFolderService;
import com.byfan.photos.service.NotepadService;
import com.byfan.photos.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: FBY
 * @Date: 2020/5/2 10:14
 * @Version 1.0
 */
@RestController
@RequestMapping("/notefolder")
public class NoteFolderController {

    @Autowired
    private NoteFolderService nfs;

    @Autowired
    private NotepadService nps;

    /**
     * 创建文件夹
     * @param noteFolder
     * @return
     */
    @RequestMapping("/creat.do")
    public NoteFolder creatNoteFolder(NoteFolder noteFolder)    {
        noteFolder.setNumber(0);
        return nfs.save(noteFolder);
    }

    /**
     * 编辑文件夹(重命名)
     * @param noteFolder
     * @return
     */
    @RequestMapping("/update.do")
    public NoteFolder updateNoteFolder(NoteFolder noteFolder){
        NoteFolder nf = nfs.findById(noteFolder.getId());
        MyUtil.copyPropertiesIgnoreNull(noteFolder,nf);
        return nfs.save(nf);
    }

    /**
     * 取消文件夹密码
     * @param noteFolder
     * @return
     */
    @RequestMapping("/cancelPassword.do")
    public NoteFolder cancelPassword(NoteFolder noteFolder){
        NoteFolder nf = nfs.findById(noteFolder.getId());
        MyUtil.copyPropertiesIgnoreNull(noteFolder,nf);
        nf.setPassword(null);
        return nfs.save(nf);
    }

    /**
     * 根据id查询文件夹
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public NoteFolder findById(Integer id){
        return nfs.findById(id);
    }

    /**
     * 删除文件夹
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public Map deleteById(Integer id){
        // 查出文件夹信息
        NoteFolder noteFolder = nfs.findById(id);
        // 创建记事本对象
        Notepad notepad = new Notepad();
        notepad.setFolderId(id);
        notepad.setOpenId(noteFolder.getOpenId());
        // 查询该问价夹下的记事本
        List<Notepad> list = nps.findByExample(notepad);
        // 进行遍历，状态改为0，表示删除
        for( Notepad np : list){
            np.setStatus(0);
            np.setFolderId(0);
            np.setClassify("回收站");
            nps.save(np);
        }
        // 从数据库删除文件夹
        nfs.deleteById(id);
        Map map = new HashMap();
        map.put("status",1);
        map.put("msg","ok");
        return map;
    }

    /**
     * 查找用户所有文件夹
     * @param openId
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<NoteFolder> findAll(String openId){
        NoteFolder nf = new NoteFolder();
        nf.setOpenId(openId);
        List<NoteFolder> list = nfs.findAll(nf);
        // 创建记事本查询对象
        Notepad np = new Notepad();
        np.setOpenId(openId);
        np.setStatus(0);
        // 查询已被软删除的记事本有多少个
        Integer trashNumber = nps.findByExample(np).size();
        // 创建回收站文件夹
        NoteFolder trash = new NoteFolder();
        trash.setId(0);     // 临时回收站id为0
        trash.setName("回收站");
        trash.setOpenId(openId);
        trash.setNumber(trashNumber);
        // 将回收站文件夹放到返回的列表中
        list.add(trash);
        return list;
    }
}
