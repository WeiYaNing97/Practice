package com.practice.minio.config;

import com.practice.minio.utils.MinioUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: xxkfz-minio
 * @ClassName Init.java
 * @author: wust
 * @create: 2024-03-16 10:34
 * @description: 项目启动初始化配置
 **/
@Component
@Slf4j
public class InitConfig implements InitializingBean {

    @Autowired
    private MinioUtils minioUtils;


    @Autowired
    private MinioConfig minioConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 项目启动创建Bucket，不存在则进行创建
        minioUtils.createBucket(minioConfig.getBucketName());
    }
}
