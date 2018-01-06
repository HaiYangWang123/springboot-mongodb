package com.hella.base.impl;

import com.hella.base.MongodbBaseService;
import com.hella.entity.MongodbBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author why10
 * @Email:haiyang.wang01@mljr.com
 * @Description:
 * @Date: 2018/1/6 13:55.
 */
@Service
public class MongodbBaseServiceImpl<T> implements MongodbBaseService<T> {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public T saveMongodb(T entity, String collectionName) {
    mongoTemplate.insert(entity, collectionName);
    return entity;
  }

  @Override
  public List<Map> getData(String collectionName) {
    return  mongoTemplate.find(new Query(), Map.class,collectionName);
  }

}
