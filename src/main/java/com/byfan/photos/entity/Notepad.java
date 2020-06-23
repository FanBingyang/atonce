package com.byfan.photos.entity;

/**
 * @Author: FBY
 * @Date: 2020/2/26 20:36
 * @Version 1.0
 */

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 记事本实例
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notepad")
public class Notepad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;   //记事本唯一id

    @Column(name = "title")
    private String title;   //记事本标题

    @Column(name = "delta")
    private String delta;   //记事本的delta

    @Column(name = "html")
    private String html;   //记事本html文本

    @Column(name = "text")
    private String text;   //记事本传纯文本

    @Column(name = "classify")
    private String classify;   //记事本分类

    @Column(name = "folderid")
    private Integer folderId;   // 记事本文件夹id

    @Column(name = "openid")
    private String openId;   //用户openid

    @Column(name = "status")
    private Integer status;   //记事本状态,1：正常  0：软删除

    @CreatedDate
    @Column(name = "creat_time")
    private Date creatTime;   //记事本创建时间

    @LastModifiedDate
    @Column(name = "last_time")
    private Date lastTime;   //记事本最后一次修改时间


}
