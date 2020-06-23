package com.byfan.photos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: FBY
 * @Date: 2020/2/26 20:17
 * @Version 1.0
 */

/**
 * 相册实体类
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;   //相册唯一id

    @Column(name = "name")
    private String name;   //相册名称

    @Column(name = "description")
    private String description;   //相册描述

    @Column(name = "cover_img")
    private String coverImg;   //相册封面图片地址路径

    @Column(name = "openid")
    private String openId;   //用户openid

    @Column(name = "number")
    private Integer number;   // 相册中照片的数量

    @CreatedDate
    @Column(name = "creat_time")
    private Date creatTime;   //相册创建时间

    @LastModifiedDate
    @Column(name = "last_time")
    private Date lastTime;  //相册最后一次操作时间
}
