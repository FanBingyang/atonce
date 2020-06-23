package com.byfan.photos.controller;

import com.byfan.photos.entity.Album;
import com.byfan.photos.entity.Photo;
import com.byfan.photos.service.AlbumService;
import com.byfan.photos.service.PhotoService;
import com.byfan.photos.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: FBY
 * @Date: 2020/2/27 11:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotoService photoService;

    /**
     * 创建相册
     * @param album
     * @return
     */
    @RequestMapping("/creat.do")
    public Album creatAlbum(Album album, @RequestParam(required = false,defaultValue = "/default.jpg")String coverImg){
        album.setCoverImg(coverImg);
        album.setNumber(0);
        return albumService.save(album);
    }

    /**
     * 编辑相册信息
     * @param album
     * @return
     */
    @RequestMapping("/update.do")
    public Album updateAlbum(Album album){
        Album album2 = albumService.findById(album.getId());
        MyUtil.copyPropertiesIgnoreNull(album,album2);
        return albumService.save(album2);
    }

    /**
     * 根据id查询相册
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public Album findById(Integer id){
        return albumService.findById(id);
    }

    /**
     * 根据id删除相册
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public Map deleteById(Integer id){
        //查出相册信息
//        Album album = albumService.findById(id);
        //创建相片对象
        Photo photo = new Photo();
//        photo.setOpenId(album.getOpenId());  //设置用户id
        photo.setAlbumId(id);   //设置相册id
        //查询该相册下的相片
        List<Photo> list = photoService.findByExample(photo);
        //进行遍历，状态改为0，表示以删除,相册id改为0，表示回收站
        for (Photo p:list){
            p.setAlbumId(0);
            p.setStatus(0);
            p.setClassify("回收站");
            photoService.save(p);
        }
        //从数据库删除相册
        albumService.deleteById(id);
        Map map = new HashMap();
        map.put("status",1);
        map.put("msg","ok");
        return map;
    }

    /**
     * 查找用户所有相册
     * @param openId
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<Album> findAll(String openId){
        Album album = new Album();
        album.setOpenId(openId);
        List<Album> list = albumService.findAll(album);
        // 创建查询对象
        Photo p = new Photo();
        p.setOpenId(openId);
        p.setStatus(0);
        // 查询该用户回收站照片的数量
        Integer trashNumber = photoService.findByExample(p).size();
        // 创建回收站对象
        Album trashAlbum = new Album();
        trashAlbum.setName("回收站");
        trashAlbum.setId(0);
        trashAlbum.setNumber(trashNumber);
        trashAlbum.setCoverImg("/trash.png");
        trashAlbum.setOpenId(openId);
        // 将回收站相册放到返回的列表中
        list.add(trashAlbum);
        return list;
    }

}
