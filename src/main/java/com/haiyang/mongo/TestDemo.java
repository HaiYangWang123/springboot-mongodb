package com.haiyang.mongo;

import com.haiyang.mongo.entity.User;
import org.joda.time.DateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

/**
 * @ClassName: TestDemo
 * @Description:
 * @Author: hywang
 * @CreateDate: 2019-05-15 10:42
 * @Version: 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
      /*  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppMain.class);
        MongoTemplate mongoTemplate = (MongoTemplate) ac.getBean("mongoTemplate");
        // mongoTemplate
        String name = mongoTemplate.getDb().getName();
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        System.out.println(name);
        System.out.println(collectionNames);


        //insert
        User remberBean = new User();
        mongoTemplate.insert(remberBean, "c_test001");


        DateTime.now().minusMinutes(2).toDate();
        Query query = new Query();
        Criteria criteria = Criteria.where("updateTime").lte(new Date());
        query.addCriteria(criteria);

        List<Map> remberBeans = mongoTemplate.find(query, Map.class, "c_test001");

        remberBeans.forEach(bean -> {
            System.out.println(bean.toString());
        });*/



    }
}
