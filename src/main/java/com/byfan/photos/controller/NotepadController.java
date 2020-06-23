package com.byfan.photos.controller;

import com.byfan.photos.entity.NoteFolder;
import com.byfan.photos.entity.Notepad;
import com.byfan.photos.service.NoteFolderService;
import com.byfan.photos.service.NotepadService;
import com.byfan.photos.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: FBY
 * @Date: 2020/2/29 22:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/notepad")
public class NotepadController {
    @Autowired
    private NotepadService notepadService;

    @Autowired
    private NoteFolderService nfs;

    /**
     * 创建新的记事本
     * @param notepad
     * @return
     */
    @RequestMapping("/creat.do")
    public Notepad creat(Notepad notepad){
        // 先查询出创建的所在文件夹
        NoteFolder nf = nfs.findById(notepad.getFolderId());
        // 文件夹中记事本的数量加1
        nf.setNumber(nf.getNumber() + 1);
        nfs.save(nf);
        // 记事本状态为1，进行保存
        notepad.setStatus(1);
        notepad.setClassify(nf.getName());
        return notepadService.save(notepad);
    }

    /**
     * 修改记事本信息
     * @param notepad
     * @return
     */
    @RequestMapping("/update.do")
    public Notepad update(Notepad notepad){
        Notepad n = notepadService.findById(notepad.getId());
        MyUtil.copyPropertiesIgnoreNull(notepad,n);
        // 删除新记事本不存在的原记事本图片
        // 分别找出新旧记事本中的图片文件名
        List<String> oldList = MyUtil.getImgName(n.getHtml());
        List<String> newList = MyUtil.getImgName(notepad.getHtml());
        // 将不在新记事本中的旧图片删除
        for (String img:oldList){
            // 判断旧图片是否在新图片列表中，取反
            if (!newList.contains(img)){
                // 不在那就删除当前图片
                MyUtil.deleteFile(img);
            }
        }
        return notepadService.save(n);
    }

    /**
     * 软删除记事本
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public Map delete(@RequestParam(value = "list",required = false)List<Integer> list,@RequestParam(value = "id",required = false)Integer id){
        Map map = new HashMap();
        // 通过id单个进行删除
//        if (id != null){
//            Notepad notepad = notepadService.findById(id);
//            notepad.setStatus(0);
//            notepadService.save(notepad);
//            map.put("msg","ok");
//            map.put("status",1);
//        }
        // 批量删除
//        else if (list!=null && !list.isEmpty()){
        for (Integer idd : list){
            Notepad notepad = notepadService.findById(idd);
            // 查出所在文件夹信息
            NoteFolder nf = nfs.findById(notepad.getFolderId());
            notepad.setStatus(0);
            // 设置文件夹id为0（回收站）
            notepad.setFolderId(0);
            notepad.setClassify("回收站");
            notepadService.save(notepad);

            // 设置文件夹的数量为原数量减1
            nf.setNumber(nf.getNumber() - 1);
            nfs.save(nf);
        }

        map.put("msg","ok");
        map.put("status",1);
//        }
        return map;
    }

    /**
     * 恢复/移动记事本
     * @param list
     * @param folderId 目标文件夹id
     * @return
     */
    @RequestMapping("/move.do")
    public Map move(@RequestParam(value = "list",required = true)List<Integer> list,Integer folderId){
        // 查询出文件夹信息
        NoteFolder noteFolder = nfs.findById(folderId);
        // 遍历操作
        for(Integer id:list){
            // 查出记事本
            Notepad notepad = notepadService.findById(id);
            // 查出原文件夹，将文件夹中记事本数量减1
            NoteFolder nf = nfs.findById(notepad.getFolderId());
            nf.setNumber(nf.getNumber() - 1);
            nfs.save(nf);
            // 更改记事本状态和所属文件夹信息
            notepad.setFolderId(folderId);
            notepad.setClassify(noteFolder.getName());
            notepadService.save(notepad);
            // 更新新的文件夹中记事本的数量
            noteFolder.setNumber(noteFolder.getNumber() + 1);
        }
        // 保存文件夹最新信息
        nfs.save(noteFolder);

        Map map = new HashMap();
        map.put("status",1);
        map.put("msg","ok");
        return map;
    }



    /**
     * 恢复记事本
     * @param list
     * @param folderId 目标文件夹id
     * @return
     */
    @RequestMapping("/restore.do")
    public Map restore(@RequestParam(value = "list",required = true)List<Integer> list,Integer folderId){
        // 查询出文件夹信息
        NoteFolder noteFolder = nfs.findById(folderId);
        // 遍历操作
        for(Integer id:list){
            // 查出记事本
            Notepad notepad = notepadService.findById(id);
            // 更改记事本状态和所属文件夹信息
            notepad.setStatus(1);
            notepad.setFolderId(folderId);
            notepad.setClassify(noteFolder.getName());
            notepadService.save(notepad);
            // 更新新的文件夹中记事本的数量
            noteFolder.setNumber(noteFolder.getNumber() + 1);
        }
        // 保存文件夹最新信息
        nfs.save(noteFolder);

        Map map = new HashMap();
        map.put("status",1);
        map.put("msg","ok");
        return map;
    }

    /**
     * 删除记事本文件
     * @param list
     * @param id
     * @return
     */
    @RequestMapping("/deletefile.do")
    public Map deleteFile(@RequestParam(value = "list",required = true)List<Integer> list,@RequestParam(value = "id",required = false)Integer id){
        Map map = new HashMap();
        // 根据id单个删除
//        if(id!=null){
//            Notepad notepad = notepadService.findById(id);
//            //找到记事本中的图片地址，进行图片删除
//            List<String> pathlist = MyUtil.getImgName(notepad.getHtml());
//            //删除所有相关图片文件
//            for(String filename : pathlist){
//                MyUtil.deleteFile(filename);
//            }
//            //从数据库中删除记录
//            notepadService.deleteById(id);
//            map.put("msg","ok");
//            map.put("status",1);
//        }
        // 批量删除
//        else if(list!=null && !list.isEmpty()){
            for(Integer idd:list){
                Notepad notepad = notepadService.findById(idd);
                //找到记事本中的图片地址，进行图片删除
                List<String> pathlist = MyUtil.getImgName(notepad.getHtml());
                //删除所有相关图片文件
                for(String filename : pathlist){
                    MyUtil.deleteFile(filename);
                }
                //从数据库中删除记录
                notepadService.deleteById(id);
                map.put("msg","ok");
                map.put("status",1);
            }
//        }
        return map;
    }



    /**
     * 根据id查寻记事本
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public Notepad findById(Integer id){
        return notepadService.findById(id);
    }

    /**
     * 根据条件查询记事本
     * @param notepad
     * @return
     */
    @RequestMapping("/findExample.do")
    public List<Notepad> findExample(Notepad notepad){
        notepad.setStatus(1);
        List<Notepad> list = notepadService.findByExample(notepad);
        return list;
    }

    /**
     * 根据用户openid分页查询所有记事本
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/selectPageAll.do")
    public List<Notepad> selectPageAll(String openId,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(required = false,defaultValue = "30")Integer size){
        System.out.println("openId="+openId+"\npage="+page+"\nsize="+size);
        return notepadService.selectPageAll(openId,page,size);
    }

    /**
     * 根据文件夹id分页查询所有记事本
     * @param folderId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/selectPage.do")
    public List<Notepad> selectPageAll(Integer folderId,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(required = false,defaultValue = "30")Integer size){
        return notepadService.selectPage(folderId,page,size);
    }

    /**
     * 查看用户回收站里的记事本
     * @param openId
     * @return
     */
//    @RequestMapping("/trash.do")
//    public List<Notepad> findTrash(String openId){
//        Notepad n = new Notepad();
//        n.setOpenId(openId);
//        n.setStatus(0);
//        n.setFolderId(0);
//        List<Notepad> list = notepadService.findByExample(n);
//        return list;
//    }

    /**
     * 根据用户openid分页查询回收站记事本
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/trash.do")
    public List<Notepad> selectTrash(String openId,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(defaultValue = "30")Integer size){
        return notepadService.selectTrash(openId,page,size);
    }

    /**
     * 定时任务，每个月1号0点1分10秒执行，删除回收站中超过30天的文件
     */
    @Scheduled(cron = "10 1 0 1 * ?")
    public void autoDelete(){
        //创建查询对象条件
        Notepad n = new Notepad();
        n.setStatus(0);
        //获取当前时间
        Date nowTime = new Date();
        //查询回收站里的文件
        List<Notepad> list = notepadService.findByExample(n);
        for (Notepad notepad:list){
            Integer daysBetween = MyUtil.daysBetween(notepad.getLastTime(),nowTime);
            if(daysBetween > 30){
                //找到记事本中的图片地址，进行图片删除
                List<String> pathlist = MyUtil.getImgName(notepad.getHtml());
                //删除所有相关图片文件
                for(String filename : pathlist){
                    MyUtil.deleteFile(filename);
                }
                //删除记录
                notepadService.deleteById(notepad.getId());
            }
        }
    }

}
