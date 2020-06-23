package com.byfan.photos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author: FBY
 * @Date: 2020/2/26 21:33
 * @Version 1.0
 */
/*
    该类是一个基础类，继承了JpaRepository，也可以继承其他实体类需要共同继承的类，这样实体类就只需要继承该类即可
 */
//@NoRepositoryBean:这个注解如果配置在继承了JpaRepository接口以及其他SpringDataJpa内部的接口的子接口时，子接口不被作为一个Repository创建代理实现类。
@NoRepositoryBean
public interface BaseRepository<T,PK extends Serializable> extends JpaRepository<T,PK> {
}
