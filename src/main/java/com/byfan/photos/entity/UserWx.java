package com.byfan.photos.entity;

/**
 * @Author: FBY
 * @Date: 2020/2/26 20:25
 * @Version 1.0
 */

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_wx")
public class UserWx implements Serializable {
    @Id
    @Column(name = "openid")
    private String openId;   //用户唯一id

    @Column(name = "nick_name")
    private String nickName;   //用户昵称

    @Column(name = "gender")
    private Integer gender;   //用户性别，0:未知;1:男性;2:女性;

    @Column(name = "avatarurl")
    private String avatarUrl;   //用户头像地址路径

    @Column(name = "address")
    private String address;   //用户注册地区  China/Henan/Zhengzhou

    @CreatedDate
    @Column(name = "creat_time")
    private Date creatTime;   //用户注册小程序时间

}
