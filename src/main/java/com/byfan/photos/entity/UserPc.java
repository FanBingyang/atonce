package com.byfan.photos.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: FBY
 * @Date: 2020/3/2 10:27
 * @Version 1.0
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_pc")
public class UserPc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;   //登录名

    @Column(name = "password")
    private String password;    //登陆密码

    @Column(name = "avatarurl")
    private String avatarUrl;   //用户头像地址路径

    @Column(name = "openid")
    private String openId;   //用户微信唯一id

    @CreatedDate
    @Column(name = "creat_time")
    private Date creatTime;   //注册时间
}
