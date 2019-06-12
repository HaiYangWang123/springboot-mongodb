package com.haiyang.mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author why10
 * @Email:hywang2017@qq.com
 * @Description:
 * @Date: 2018/1/6 13:39.
 */
@Configuration
@EnableConfigurationProperties
public class MongodbConfig {
    @Value("${mongodb.uri:mongodb://root:root@127.0.0.1:27017/admin}")
    private String uri;
    @Value("${mongodb.database:test}")
    private String database;

    @Bean
    public MongoClient mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI(uri, builder());
        return new MongoClient(mongoClientURI);
    }


    @Bean
    public MongoTemplate mongoTemplate() {
        MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient(), database);
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory), new MongoMappingContext());
        //key中如果存在 .等特殊字符，用_替换
        //converter.setMapKeyDotReplacement("_specialchar_");
        //converter.setMapKeyDotReplacement("_");
        //去掉存储中的_class
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        // 自定义类型转换器
        List<Converter> converters = new ArrayList();

        //将时间date类型 写如mongodb中转换为 string类型
        //converters.add(new WritingDateToString());
        // converters.add(new WritingDoubleToInteger());

        converters.add(new DoubleToInteger());
        converters.add(new IntegerToDouble());

        CustomConversions customConversions = new CustomConversions(CustomConversions.StoreConversions.of(SimpleTypeHolder.DEFAULT), converters);
        converter.setCustomConversions(customConversions);
        MongoTemplate mongoTemplate = new MongoTemplate(factory, converter);
        return mongoTemplate;
    }

    private MongoClientOptions.Builder builder() {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        //连接配置：
        //    .connectTimeout(3000)//在超时之前可以打开连接多长时间。
        //  .socketTimeout(3000)//在超时之前，套接字上的发送或接收可以花多长时间。
        //   .maxConnectionIdleTime(30000) //池连接的最大空闲时间。超出此限制的连接将被关闭
        //   .maxConnectionLifeTime(30000);//池化连接的最长使用寿命。超出此限制的连接将被关闭;
        //  builder.connectionsPerHost(30);//设置每个主机的最大连接数。


        return builder;
    }


    // Direction: Java -> MongoDB
 /*   @WritingConverter
    public class DateToString implements Converter<Date, String> {
        @Override
        public String convert(Date date) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            return sf.format(date);
        }
    }

    // Direction: MongoDB -> Java
    @ReadingConverter
    public class StringToDate implements Converter<String, Date> {

        @Override
        public Date convert(String str) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            try {
                return sf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return new Date();
            }
        }
    }*/


    @WritingConverter
    public class DoubleToInteger implements Converter<Double, Integer> {
        @Override
        public Integer convert(Double aDouble) {
            // 会丢失精度
            return aDouble.intValue();
        }
    }

    @ReadingConverter
    public class IntegerToDouble implements Converter<Integer, Double> {

        @Override
        public Double convert(Integer integer) {
            // 会丢失精度
            return integer.doubleValue();
        }
    }

/*
    @WritingConverter
    public class DateTimeToString implements Converter<Date, String> {
        @Override
        public String convert(Date date) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            return sf.format(date);
        }
    }*/

}
