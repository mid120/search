package com.liw.elasticsearch.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * @Description: (用一句话描述该文件做什么)
 * @author: zhuchunlan
 * @date: 2018/3/15 13:49
 * <p>
 * Copyright 2010-2018 TimaNetworks Inc. All rights reserved.
 */
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

    public String title = "用户行为统计系统";

    public String description = "用户行为统计系统";

    public String basePackage = "com.liw.elasticsearch";
}
