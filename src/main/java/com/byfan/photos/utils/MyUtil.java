package com.byfan.photos.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: FBY
 * @Date: 2020/2/29 16:45
 * @Version 1.0
 */
public class MyUtil {

    // 获取对象中属性为null的属性名
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 将源对象中的属性值copy到目标对象中相同的属性上，copy的时候忽略值为null的属性，返回合并之后的对象
     * @param src  源对象
     * @param target  目标对象
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 删除磁盘文件，传入保存的路径  eg: E:/1.txt
     * @param filePath
     * @return
     */
    public static String deleteFile(String filePath){
        String flag = "error";
        File file = new File(filePath);
        System.out.println(filePath);
        // 判断文件是否存在并且不为空
        if (file.isFile() && file.exists()){
            file.delete();
            flag = "ok";
        }
        return flag;
    }

    /**
     * 获取html文本字符串中的 /imgName.jpg 地址
     * @param str
     * @return
     */
    public static List<String> getImgName(String str){
        //正则表达式，匹配：/(***).jpg
        String re = "/([^/])*\\.jpg";
        //创建对象
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(str);

        List<String> list = new ArrayList<>();

        while (matcher.find()){
            list.add(matcher.group());
        }
        return list;
    }


    /**
     * 返回两个时间直接相差的天数
     * @param beginTime 起始时间
     * @param endTime 结束时间
     * @return
     */
    public static Integer daysBetween(Date beginTime,Date endTime){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //格式化时间 parse():将字符串转换成时间   format():将时间转换成对应格式的字符串
            beginTime = sdf.parse(sdf.format(beginTime));
            endTime = sdf.parse(sdf.format(endTime));
            //获取Calendar对象
            Calendar cal = Calendar.getInstance();
            cal.setTime(beginTime);             //设置开始时间
            long time1 = cal.getTimeInMillis(); //获取开始时间的秒数
            cal.setTime(endTime);               //设置结束时间
            long time2 = cal.getTimeInMillis(); //获取结束时间的描述
            //计算两个时间相差的天数，相差的秒数除以一天的总秒数
            long between_days = (time2 - time1) / (1000*3600*24);

            return Integer.parseInt(String.valueOf(between_days));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
