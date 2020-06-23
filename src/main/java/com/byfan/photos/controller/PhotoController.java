package com.byfan.photos.controller;

import com.byfan.photos.entity.Album;
import com.byfan.photos.entity.Photo;
import com.byfan.photos.service.AlbumService;
import com.byfan.photos.service.PhotoService;
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
 * @Date: 2020/2/27 15:52
 * @Version 1.0
 */
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @Autowired
    private AlbumService albumService;


    /**
     * 上传照片信息接口
     * @param photo
     * @return
     */
    @RequestMapping("/creat.do")
    public Photo creat(Photo photo){
        // 对照片所属的相册进行操作，数量+1
        Album album = albumService.findById(photo.getAlbumId());
        album.setNumber(album.getNumber() + 1);
        albumService.save(album);
        photo.setStatus(1);
        photo.setClassify(album.getName());
        return photoService.save(photo);
    }

    /**
     * 修改相片信息
     * @param photo
     * @return
     */
    @RequestMapping("/update.do")
    public Photo update(Photo photo){
        Photo p = photoService.findById(photo.getId());
        MyUtil.copyPropertiesIgnoreNull(photo,p);
        return photoService.save(p);
    }

    /**
     * 将照片批量/单个移动到指定相册
     * @param list
     * @param albumId
     * @return
     */
    @RequestMapping("/move.do")
    public Map move(@RequestParam(value="list")List<Integer> list,Integer albumId){
        // 查出新相册信息
        Album newAlbum = albumService.findById(albumId);
        // 原相册id
        Integer oldAlbumId = null;
        for (Integer id:list){
            Photo p = photoService.findById(id);
            // 保存原先相册id
            oldAlbumId = p.getAlbumId();
            p.setClassify(newAlbum.getName());
            p.setAlbumId(albumId);
            photoService.save(p);

        }
        // 查出原相册信息
        Album oldAlbum = albumService.findById(oldAlbumId);
        // 原相册数量减少操作的list的长度
        oldAlbum.setNumber(oldAlbum.getNumber() - list.size());
        // 新相册数量增加操作的list的长度
        newAlbum.setNumber(newAlbum.getNumber() + list.size());
        // 更新新旧相册信息
        albumService.save(newAlbum);
        albumService.save(oldAlbum);

        Map map = new HashMap();
        map.put("status","1");
        map.put("msg","ok");
        return map;
    }

    /**
     * 进行照片的复制，将照片复制到已有相册
     * @param list
     * @param albumId
     * @return
     */
    @RequestMapping("/copy.do")
    public Map copy(@RequestParam(value="list",required=false)List<Integer> list,Integer albumId){

        // 查出目标相册信息
        Album album = albumService.findById(albumId);
        for(Integer id:list){
            Photo p = photoService.findById(id);
//            // 创建新得照片信息，并进行信息复制
            Photo photo = new Photo();
            photo.setOpenId(p.getOpenId());
            photo.setDescription(p.getDescription());
            photo.setImgPath(p.getImgPath());
            photo.setStatus(p.getStatus());
            photo.setClassify(album.getName());
            photo.setAlbumId(albumId);
            photoService.save(photo);
        }
        // 更新目标相册数量信息,
        album.setNumber(album.getNumber() + list.size());
        albumService.save(album);

        Map map = new HashMap();
        map.put("status","1");
        map.put("msg","ok");
        return map;
    }


    /**
     * 通过id查询照片
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public Photo findById(Integer id){
        return photoService.findById(id);
    }


    /**
     * 根据照片id软删除
     * @param list 传入id列表，批量软删除
     * @param id 传入id，单个删除
     * @return
     */
    @RequestMapping("/delete.do")
    public Map deletelist(@RequestParam(value="list",required=false)List<Integer> list,@RequestParam(value="id",required=false)Integer id){
        Map map = new HashMap();

        // 根据id单个删除
//        if (id != null){
//            Photo photo = photoService.findById(id);
//            photo.setStatus(0);
//            photoService.save(photo);
//            map.put("status","1");
//            map.put("msg","ok");
//        }
//        else if (list!=null && !list.isEmpty()){
        //批量删除
        // 原相册id
        Integer albumId = null;
        for (Integer idd : list){
            Photo photo = photoService.findById(idd);
            albumId = photo.getAlbumId();
            photo.setStatus(0);
            photo.setAlbumId(0);
            photo.setClassify("回收站");
            photoService.save(photo);
        }
        // 修改原相册信息
        Album album = albumService.findById(albumId);
        album.setNumber(album.getNumber() - list.size());
        albumService.save(album);

        map.put("status","1");
        map.put("msg","ok");
//        }
        return map;
    }

    /**
     * 恢复照片
     * @param list
     * @param albumId
     * @return
     */
    @RequestMapping("/restore.do")
    public Map restore(@RequestParam(value = "list",required = true)List<Integer> list,Integer albumId){
        // 查出新相册信息
        Album newAlbum = albumService.findById(albumId);
        for (Integer id:list){
            Photo p = photoService.findById(id);
            p.setClassify(newAlbum.getName());
            p.setAlbumId(albumId);
            p.setStatus(1);
            photoService.save(p);
        }
        // 新相册数量增加操作的list的长度
        newAlbum.setNumber(newAlbum.getNumber() + list.size());
        // 更新新旧相册信息
        albumService.save(newAlbum);
        Map map = new HashMap();
        map.put("status","1");
        map.put("msg","ok");
        return map;
    }


    /**
     * 删除照片文件
     * @param list 传入id列表，批量删除
     * @param id 传入id，单个删除
     * @return
     */
    @RequestMapping("/deletefile.do")
    public Map deleteFile(@RequestParam(value="list",required=false)List<Integer> list,@RequestParam(value="list",required=false)Integer id){
        Map map = new HashMap();
        // 单个删除
//        if(id!=null){
//            Photo photo = photoService.findById(id);
//            String msg = MyUtil.deleteFile(photo.getImgPath());
//            map.put("msg",msg);
//            if (msg.equals("ok")){
//                photoService.deleteById(id);
//            }
//        }
//        else if(list!=null && !list.isEmpty()){
        // 批量删除
        for(Integer idd:list){
            Photo p = photoService.findById(idd);
            String msg = MyUtil.deleteFile(p.getImgPath());
            map.put("msg",msg);
            if (msg.equals("ok")){
                photoService.deleteById(idd);
            }
        }
//        }
        return map;
    }

    /**
     * 查询某个相册的照片
     * @param photo
     * @return
     */
    @RequestMapping("/findByAlbumId.do")
    public List<Photo> findByByExample(Photo photo){
        photo.setStatus(1);
        List<Photo> list = photoService.findByExample(photo);
        return list;
    }

    /**
     * 根据相册id分页查询照片
     * @param albumId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/selectPage.do")
    public List<Photo> selectPage(Integer albumId,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(required = false,defaultValue = "30")Integer size){
        return photoService.selectPage(albumId,page,size);
    }

    /**
     * 查询回收站照片
     * @param photo
     * @return
     */
//    @RequestMapping("/trash.do")
//    public List<Photo> findTrash(Photo photo){
//        photo.setStatus(0);
//        List<Photo> list = photoService.findByExample(photo);
//        return list;
//    }

    /**
     * 根据用户opendi分页查询
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/trash.do")
    public List<Photo> selectTrash(String openId,@RequestParam(required = false,defaultValue = "1")Integer page,@RequestParam(required = false,defaultValue = "30")Integer size){
        return photoService.selectTrash(openId,page,size);
    }

    /**
     * 定时任务，每个月1号0点0分10秒执行，删除回收站中超过30天的文件
     */
    @Scheduled(cron = "10 0 0 1 * ?")
    public void autoDelete(){
        //设置查询对象的条件
        Photo p = new Photo();
        p.setStatus(0);
        //当前时间
        Date nowTime = new Date();
        //查询回收站里的所有文件
        List<Photo> list = photoService.findByExample(p);
        for (Photo photo:list){
            Integer daysBetween = MyUtil.daysBetween(photo.getLastTime(),nowTime);
            if(daysBetween > 30){
                //删除文件
                MyUtil.deleteFile(photo.getImgPath());
                //删除记录
                photoService.deleteById(photo.getId());
            }
        }
    }
}
