package com.byfan.photos.service.Impl;

import com.byfan.photos.entity.Photo;
import com.byfan.photos.jpa.PhotoJpa;
import com.byfan.photos.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: FBY
 * @Date: 2020/2/26 22:07
 * @Version 1.0
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoJpa photoJpa;


    /**
     * 保存/修改/软删除照片
     * @param photo
     * @return
     */
    @Override
    public Photo save(Photo photo) {
        return photoJpa.save(photo);
    }

    /**
     * 根据id删除照片
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        photoJpa.deleteById(id);
    }

    /**
     * 根据id查找照片
     * @param id
     * @return
     */
    @Override
    public Photo findById(Integer id) {
        //findById(Integer id)返回封装后的对象Optional<T>，
        Optional<Photo> optional = photoJpa.findById(id);
        // 在Optional类中有很多内置的方法，其中isPresen()方法返回Optional对象是否为null的结果,如果当前对象有值就返回true，否则返回false，
        if(optional.isPresent()){
            //当结果有值时，然后调用它的get()方法，会返回一个类型的实体类对象
            return optional.get();
        }
        return null;
    }


    /**
     * 根据分类名称查询照片
     * @param photo
     * @return
     */
    @Override
    public List<Photo> findByExample(Photo photo) {
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()  //构建对象
                .withMatcher("openId",match -> match.exact())     //"openId"精确匹配
                .withMatcher("status",match -> match.exact())     //"状态"精确匹配
                .withMatcher("albumId",match -> match.exact())    //"相册id"精确匹配
//                .withMatcher("albumId",ExampleMatcher.GenericPropertyMatchers.exact())   //采用精确匹配，和上面语句作用相同
                .withIgnoreNullValues();   //忽略空字段
        //创建实例
        Example<Photo> example = Example.of(photo,matcher);
        //创建排序对象,降序desc，升序asc
//        Sort.Direction sort_Direction = Sort.Direction.DESC;
        //设置排序对象,更具创建时间倒序
        Sort sort = Sort.by(Sort.Direction.DESC,"creatTime");
        return photoJpa.findAll(example,sort);
    }

    /**
     * 根据相册id分页查询照片数据
     * @param albumId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Photo> selectPage(Integer albumId, Integer page, Integer size) {
        return photoJpa.selectPage(albumId,(page-1)*size,size);
    }

    /**
     * 根据用户openid分页查询照片
     * @param openId
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Photo> selectTrash(String openId, Integer page, Integer size) {
        return photoJpa.selectTrash(openId,(page-1)*size,size);
    }
}
