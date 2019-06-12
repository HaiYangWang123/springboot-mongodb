package com.haiyang.mongo.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @ClassName: User
 * @Description:
 * @Author: hywang
 * @CreateDate: 2019-05-29 10:12
 * @Version: 1.0
 */

public class User extends Person {

    private String username;
    private String password;
    private int age;
    private double weight;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


}
