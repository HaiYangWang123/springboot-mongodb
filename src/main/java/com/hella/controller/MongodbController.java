package com.hella.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hella.base.MongodbBaseService;
import com.hella.entity.MongodbBean;
import com.mongodb.util.JSON;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author why10
 * @Email:haiyang.wang01@mljr.com
 * @Description:
 * @Date: 2018/1/6 14:02.
 */
@RestController
public class MongodbController {

  @Autowired
  private MongodbBaseService mongodbBaseService;

  @RequestMapping("/check")
  public String check() {
    return "check  success";
  }


  @RequestMapping(value = "/saveJson/{json}", method = RequestMethod.GET)
  public Object saveJson(@PathVariable("json") String json) {
    MongodbBean mongodbBean = new MongodbBean();
    mongodbBean.setData(json);
    mongodbBean.setCreateTime(System.currentTimeMillis());
    return mongodbBaseService.saveMongodb(mongodbBean, "test_mongodb");
  }
}
