package com.hella.base.impl;

import com.hella.base.MongodbBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

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
}
