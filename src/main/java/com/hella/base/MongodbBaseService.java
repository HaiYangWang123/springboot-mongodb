package com.hella.base;

/**
 * @Author why10
 * @Email:haiyang.wang01@mljr.com
 * @Description:
 * @Date: 2018/1/6 13:54.
 */
public interface MongodbBaseService<T> {
   T saveMongodb(T entity, String collectionName);
}
