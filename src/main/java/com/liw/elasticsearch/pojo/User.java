package com.liw.elasticsearch.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @Description: (用一句话描述该文件做什么)
 * @author: Liw
 * @date: 2018/5/17 11:02
 * <p>
 * Copyright 2010-2018 TimaNetworks Inc. All rights reserved.
 */
@Document(indexName = "es",type = "user", shards = 1,replicas = 1, refreshInterval = "-1")
    public class User {
        @Id
        private String id;
        @Field
        private String userName;
        @Field
        private String password;
        @Field(type=FieldType.Integer)
        private Integer age ;
        @Field
        private String address;
        @Field(type=FieldType.Date)
        private Date createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
