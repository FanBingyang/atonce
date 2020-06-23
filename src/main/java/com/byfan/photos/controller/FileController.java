package com.byfan.photos.controller;

import com.byfan.photos.utils.MyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: FBY
 * @Date: 2020/2/28 17:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {
    //从spring容器中获取图片的保存路径地址
    @Value("${picPath}")
    public String Dir;


    //上传图片接口
    @RequestMapping("/uploadpic.do")
    public Map test(HttpServletRequest request){

        Map map = new HashMap();
        String msg = "ok";
        Integer status = 1;
        //图片名称
        String picName = null;
        //获取文件请求
        MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;

        Iterator<String> iterator = req.getFileNames();
        try {
            while (iterator.hasNext()){
                MultipartFile multipartFile = req.getFile(iterator.next());

                File file = new File(Dir);
                if(!file.exists()){
                    file.mkdir();
                }

                //格式化时间戳
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
                String nowTime = sdf.format(new Date().getTime()).substring(2);  // 2020-02-28 18:12 --->  2002281812
                //创建UUID随机字符
                String uuid = UUID.randomUUID().toString();
                uuid = uuid.substring(uuid.lastIndexOf("-")+1);     //取最后12位

                //取得图片的格式后缀
                String originalFilename = multipartFile.getOriginalFilename();
                String picType = originalFilename.substring(originalFilename.lastIndexOf("."));
                //拼接图片名称 时间戳+uuid+.jpg
                picName = nowTime + uuid + picType;
                //写入本地
                multipartFile.transferTo(new File(Dir,picName));
            }
        } catch (IOException e) {
            e.printStackTrace();
            msg = "error";
            status = 0;
        }
        map.put("msg",msg);
        map.put("status",status);
        map.put("path","/"+picName);
        return map;
    }

    /**
     * 根据图片路径批量删除图片
     * @param list
     * @return
     */
    @RequestMapping("/deletepic.do")
    public Map deletePic(@RequestParam(value="list")List<String> list){
        Integer status = 0;
        String msg=null;
        for (String filepath:list) {
            String filename = filepath.substring(filepath.lastIndexOf("/"));
            msg = MyUtil.deleteFile(Dir+filename);
            if (msg.equals("ok")){
                status = 1;
            }
        }
        Map map = new HashMap();
        map.put("msg",msg);
        map.put("status",status);
        return map;
    }



}
