package com.haiyang.mongo.base;

import java.util.List;
import java.util.Map;

/**
 * @Author why10
 * @Email:hywang2017@qq.com
 * @Description:
 * @Date: 2018/1/6 13:54.
 */
public interface MongodbBaseService<T> {
   T saveMongodb(T entity, String collectionName);
   List<?> getData(String collectionName);
}
