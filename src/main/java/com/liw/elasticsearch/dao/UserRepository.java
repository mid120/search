package com.liw.elasticsearch.dao;
import com.liw.elasticsearch.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接口只要继承 ElasticsearchRepository 类即可。默认会提供很多实现，比如 CRUD 和搜索相关的实现。类似于 JPA 读取数据，是使用 CrudRepository 进行操作 ES 数据。支持的默认方法有： count(), findAll(), findOne(ID), delete(ID), deleteAll(), exists(ID), save(DomainObject), save(Iterable<DomainObject>)。

 另外可以看出，接口的命名是遵循规范的。常用命名规则如下：
 关键字     方法命名
 And          findByNameAndPwd
 Or             findByNameOrSex
 Is              findById
 Between   findByIdBetween
 Like           findByNameLike
 NotLike     findByNameNotLike
 OrderBy    findByIdOrderByXDesc
 Not           findByNameNot

 注意
 a. City 属性名不支持驼峰式。
 b. indexName 配置必须是全部小写，不然会出异常。
 org.elasticsearch.indices.InvalidIndexNameException: Invalid index name [provinceIndex], must be lowercase
 */
@Component
public interface UserRepository extends ElasticsearchRepository<User,String> {
    User queryUserById(String id);
    @Override
    void deleteById(String s);
    List<User> queryByAddressLike(String address);
    List<User> findAll();

}
