package com.hella.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @Author why10
 * @Email:haiyang.wang01@mljr.com
 * @Description:
 * @Date: 2018/1/6 13:39.
 */
@Configuration
public class MongodbConfig {
  @Value("${mongodb.uri}")
  private String uri;
  @Value("${mongodb.database}")
  private String database;


  @Bean(name = "MongoClient")
  public MongoClient mongoClient(){
    return new MongoClient(new MongoClientURI(uri, builder(null)));
  }
  @Bean(name = "MongoTemplate")
  public MongoTemplate mongoTemplate(){
    MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient(),database);
    return new MongoTemplate(factory);
  }
  private MongoClientOptions.Builder builder(MongoClientOptions options) {
    if (options != null) {
      return MongoClientOptions.builder(options);
    }
    return MongoClientOptions.builder();
  }
}
