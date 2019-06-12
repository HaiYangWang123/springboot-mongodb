package com.haiyang.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @Author why10
 * @Email:hywang2017@qq.com
 * @Description:
 * @Date: 2018/1/6 13:46.
 */

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class AppMain {
  public static void main(String[] args) {

    SpringApplication.run(AppMain.class,args);
    System.out.println("server start.......");
  }
}
