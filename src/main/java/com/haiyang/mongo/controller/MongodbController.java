package com.haiyang.mongo.controller;

import com.alibaba.fastjson.JSONObject;
import com.haiyang.mongo.base.MongodbBaseService;
import com.haiyang.mongo.entity.MongodbBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author why10
 * @Email:hywang2017@qq.com
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


    @RequestMapping(value = "/saveJson/{json}")
    public Object saveJson(@PathVariable("json") String json) {
        MongodbBean mongodbBean = new MongodbBean();
        mongodbBean.setData(JSONObject.parse(json));
        mongodbBean.setCreateTime(new Date());
        return mongodbBaseService.saveMongodb(mongodbBean, "test_mongodb");
    }


    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public List<?> getData() {
        return mongodbBaseService.getData("test_mongodb");
    }

}
