package com.hella.mian;

import com.hella.config.MongodbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author why10
 * @Email:haiyang.wang01@mljr.com
 * @Description:
 * @Date: 2018/1/6 13:46.
 */

@SpringBootApplication(exclude = {MongodbConfig.class})
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.hella"})
@PropertySource(value = {"classpath:/application.properties"}, encoding = "UTF-8")
public class AppMian {
  public static void main(String[] args) {

    SpringApplication.run(AppMian.class,args);
    System.out.println("server start.......");
  }
}
