package com.liw.elasticsearch.controller;

import com.liw.elasticsearch.dao.UserRepository;
import com.liw.elasticsearch.pojo.User;
import io.swagger.annotations.Api;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.net.InetSocketAddress;
import java.util.List;
/**
 * @Description: (用一句话描述该文件做什么)
 * @author: Liw
 * @date: 2018/5/17 11:04
 * <p>
 * Copyright 2010-2018 TimaNetworks Inc. All rights reserved.
 */
@RestController
@RequestMapping("/user")
@Api
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository er;

    private static TransportClient client = null;

    //增加
    @PostMapping("/add")
    public String add(@RequestBody User user){
//        CreateIndexRequest indexRequest = new CreateIndexRequest(user.getUserName());
//        client.admin().indices().create(indexRequest).actionGet();
//        closeClient();
        logger.debug("1111111111111111");
        return "success";
    }

    //删除
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") String indexName){
        IndicesAliasesRequest request = new IndicesAliasesRequest();
        IndicesAliasesRequest.AliasActions aliasActions = IndicesAliasesRequest.AliasActions.remove().index(indexName).aliases("seller_invoice_info1");
        request.addAliasAction(aliasActions);
        client.admin().indices().aliases(request);
        return "删除索引成功";
    }
    //局部更新
    @PutMapping("/update")
    public String update(@RequestBody User user){
        User entity =er.queryUserById(user.getId());
        entity.setAddress(user.getAddress());
        entity.setAge(user.getAge());
        entity.setUserName(user.getUserName());
        er.save(entity);
        return "success";
    }
    //查询
    @GetMapping("/queryById")
    public User queryById(@RequestParam("id") String id){
        User entity=er.queryUserById(id);
        return entity;
    }
    @GetMapping("/queryByAbout")
    public List<User> queryByAbout(@RequestParam("about") String address){
        List<User> accountInfos=er.queryByAddressLike(address);
        return accountInfos;
    }

    @GetMapping("/query")
    public List<User> query(){
        return er.findAll();
    }



    @PostMapping("/queryByKeys")
    public List<User> searchUser(Integer pageNumber,
                                 Integer pageSize,
                                 String userName,
                                 String address) {
        BoolQueryBuilder username = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("userName", userName));
        BoolQueryBuilder description = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("address", address));
        // 分页参数
        Pageable pageable = new PageRequest(0, 10);

        // Function Score Query
        FunctionScoreQueryBuilder functionScoreQueryBuilder1 = QueryBuilders.functionScoreQuery(username,ScoreFunctionBuilders.weightFactorFunction(1000));

        FunctionScoreQueryBuilder functionScoreQueryBuilder2 = QueryBuilders.functionScoreQuery(description,ScoreFunctionBuilders.weightFactorFunction(10));



        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder1).build();

        System.out.println(("\n searchUser(): searchContent [" + userName+" "+address + "] \n DSL  = \n " + searchQuery.getQuery().toString()));

        Page<User> searchPageResults = er.search(searchQuery);
        return searchPageResults.getContent();
    }

    /*
    在使用 Elasticsearch 进行全文搜索时，搜索结果默认会以文档的相关度进行排序，如果想要改变默认的排序规则，
    也可以通过sort指定一个或多个排序字段。
    但是使用sort排序过于绝对，它会直接忽略掉文档本身的相关度（根本不会去计算）
    在很多时候这样做的效果并不好，这时候就需要对多个字段进行综合评估，得出一个最终的排序。
     */

    /**
     *
     * @param key
     * @return
     */
    @PostMapping("/scoreQuery")
    public  List<User> searchByScore(String key){
     // 分页参数(如果 不传不会报错)
     // Pageable pageable = new PageRequest(0, 10);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("address", "黄"));


        ScoreFunctionBuilder<?> scoreFunctionBuilder = ScoreFunctionBuilders.fieldValueFactorFunction("age").modifier(FieldValueFactorFunction.Modifier.LN1P).factor(0.1f);
        FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(boolQueryBuilder,scoreFunctionBuilder).boostMode(CombineFunction.SUM);



        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(query).build();
        Page<User> search = er.search(build);
        return search.getContent();

    }


    static {
        // 通过setting对象指定集群配置信息, 配置的集群名 // 设置ES实例的名称
        Settings settings = Settings.builder().put("cluster.name", "CollectorDBCluster")
                // 自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .put("client.transport.sniff", true)
                .build();
        client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
    }


    public void  closeClient(){
        if (client != null){
            client.close();
        }
    }


}
