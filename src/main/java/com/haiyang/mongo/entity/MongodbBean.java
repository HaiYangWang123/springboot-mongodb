package com.haiyang.mongo.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @Author why10
 * @Email:hywang2017@qq.com
 * @Description:
 * @Date: 2018/1/6 14:04.
 */
public class MongodbBean {
    @Id
    private String id;// 主键ID mongo会自动生成

    private Object data;

    private Date createTime;// 入库时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
