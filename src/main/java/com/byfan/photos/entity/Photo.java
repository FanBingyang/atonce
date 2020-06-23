package com.byfan.photos.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: FBY
 * @Date: 2020/2/26 18:07
 * @Version 1.0
 */

/**
 * 照片实体类
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)  //为@CreatDate等注解有效，在类上面添加该注解
@Table(name = "photo")
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;   //照片唯一id

    @Column(name = "description")
    private String description;   //照片描述

    @Column(name = "img_path")
    private String imgPath;   //照片地址路径

    @Column(name = "classify")
    private String classify;   //照片分类

    @Column(name = "album_id")
    private Integer albumId;   //相册的id

    @Column(name = "status")
    private Integer status;    //照片状态  1：正常  0：软删除

    @Column(name = "openid")
    private String openId;   //用户openid

    @CreatedDate
    @Column(name = "creat_time")
    private Date creatTime;   //照片上传时间

    @LastModifiedDate
    @Column(name = "last_time")
    private Date lastTime;   //照片最后一次操作时间

}
