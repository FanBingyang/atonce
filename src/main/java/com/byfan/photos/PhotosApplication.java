package com.byfan.photos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing    // 为@CreatDate等注解有效，在类上面添加该注解
@EnableScheduling     // 开启定时任务
public class PhotosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotosApplication.class, args);
    }

}
