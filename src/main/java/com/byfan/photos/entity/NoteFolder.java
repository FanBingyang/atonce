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
 * @Date: 2020/5/1 19:31
 * @Version 1.0
 */

/**
 * 记事本文件夹实体类
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "notefolder")
public class NoteFolder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;   //文件夹唯一id

    @Column(name = "name")
    private String name;  // 文件夹名称

    @Column(name = "openid")
    private String openId;   //用户openid

    @Column(name = "number")
    private Integer number;      // 文件夹里记事本数量

    @CreatedDate
    @Column(name = "creat_time")
    private Date creatTime;   //文件夹创建时间

    @LastModifiedDate
    @Column(name = "last_time")
    private Date lastTime;  //文件夹最后一次操作时间
    
}
