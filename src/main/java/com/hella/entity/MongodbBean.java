package com.hella.entity;

import org.springframework.data.annotation.Id;

/**
 * @Author why10
 * @Email:haiyang.wang01@mljr.com
 * @Description:
 * @Date: 2018/1/6 14:04.
 */
public class  MongodbBean<T> {
  @Id
  private String id;// 主键ID mongo会自动生成

  private T data;

  private long createTime;// 入库时间

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "MongodbBean{" +
            "id='" + id + '\'' +
            ", data=" + data +
            ", createTime=" + createTime +
            '}';
  }
}
