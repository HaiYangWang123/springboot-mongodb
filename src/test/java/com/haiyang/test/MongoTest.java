package com.haiyang.test;

import com.alibaba.fastjson.JSONObject;
import com.haiyang.mongo.AppMain;
import com.haiyang.mongo.entity.MongodbBean;
import com.haiyang.mongo.entity.Person;
import com.haiyang.mongo.entity.User;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

/**
 * @ClassName: MongoTest
 * @Description: 测试类
 * @Author: hywang
 * @CreateDate: 2019-05-22 11:14
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})// 指定启动类
public class MongoTest {

    String collectionName = "c_test_user";
    String collectionNameTestClass = "c_test_person";
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void inertTest() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setWeight(83.2);
        user.setAge(12);
        MongodbBean bean = new MongodbBean();
        bean.setData(user);
        bean.setCreateTime(new Date());
        mongoTemplate.insert(bean, collectionName);

        //mongoTemplate.updateFirst() 修改符合条件的第一条
        //mongoTemplate.updateMulti() 符合条件的所有
        //mongoTemplate.upsert() 修改符合条件的，如果不存在则添加


        // mongoTemplate.remove()
    }

    @Test
    public void queryTest() {
        Query query = new Query();
        query.addCriteria(Criteria.where("data.username").is("zhangsan"));
        query.with(new Sort(Sort.Direction.ASC, "createTime"));
        List<JSONObject> mongodbBeans = mongoTemplate.find(query, JSONObject.class, collectionName);
        if (!CollectionUtils.isEmpty(mongodbBeans)) {
            mongodbBeans.forEach(bean -> {
                System.out.println(bean.toString());
            });
        }

    }

    @Test
    public void queryWithName() {
        Query query = new Query();
        query.fields().include("_id");
        query.fields().include("name");
        query.fields().include("createTime");
        List<Map> mongodbBeans = mongoTemplate.find(query, Map.class, collectionName);
        if (!CollectionUtils.isEmpty(mongodbBeans)) {
            mongodbBeans.forEach(bean -> {
                System.err.println(bean.toString());
            });
        }
    }

    @Test
    public void inertManyTest() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setWeight(83.2);
        user.setAge(12);
        MongodbBean bean = new MongodbBean();
        bean.setData(user);
        bean.setCreateTime(new Date());
        mongoTemplate.insert(bean, "c_many_user");

    }

    @Test
    public void testAggregation() {
        List<AggregationOperation> aggregationOperations = Lists.newArrayList();
        //MatchOperation matchOperation = match(Criteria.where("name").is("zhangsan"));

        GroupOperation groupOperation = new GroupOperation(Fields.fields("_id", "createTime"))
                .max("data.age").as("max")
                .min("data.age").as("min")
                .sum("data.age").as("sum")
                .avg("data.age").as("avg");
        //aggregationOperations.add(matchOperation);
        aggregationOperations.add(groupOperation);

        AggregationResults<Map> results = mongoTemplate.aggregate(
                Aggregation.newAggregation(aggregationOperations), "c_many_user", Map.class);
        List<Map> mapList = results.getMappedResults();
        if (!CollectionUtils.isEmpty(mapList)) {

        }

    }


    @Test
    public void testSpecialChar() {
        Map<String, String> map = new HashMap<>();
        map.put("123@qq.com", "email");
        map.put("name.first", "zhang");
        map.put("name_second", "san");
        MongodbBean bean = new MongodbBean();
        bean.setData(map);
        bean.setCreateTime(new Date());
        mongoTemplate.insert(bean, collectionName);

        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "createTime"));
        List<JSONObject> mongodbBeans = mongoTemplate.find(query, JSONObject.class, collectionName);
        if (!CollectionUtils.isEmpty(mongodbBeans)) {
            mongodbBeans.forEach(beans -> {
                System.out.println(beans.toString());
            });
        }

    }

    @Test
    public void testClass() {
       Person person = new Person();
        person.setName("lisi");
        mongoTemplate.save(person, collectionNameTestClass);

       User user = new User();
        user.setName("lisi");
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setWeight(83.2);
        mongoTemplate.save(user, collectionNameTestClass);
    }

    @Test
    public void testClassFind() {

        Query query = new Query(Criteria.where("name").is("lisi"));

        User person = mongoTemplate.findOne(query, User.class, collectionNameTestClass);

        // "_class" : "com.haiyang.mongo.entity.User"
        System.out.println(person); //com.haiyang.mongo.entity.User@153d6d74

        //无
        //com.haiyang.mongo.entity.Person@687389a6

    }

}
