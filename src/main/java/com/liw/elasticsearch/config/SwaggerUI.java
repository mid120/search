package com.liw.elasticsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by admin on 2018/3/13.
 */
@Component
public class SwaggerUI {

    @Autowired
    private SwaggerConfig config;

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfo(config.title,//大标题
                config.description,//小标题
                "1.0",//版本
                "no terms of service",
                new Contact("", "", ""),
                "钛马信息网络技术有限公司",//链接显示文字
                "http://www.timanetworks.com/"//网站链接
        );
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()  // 选择那些路径和api会生成document
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .apis(RequestHandlerSelectors.basePackage(config.basePackage))
                .build();
        return docket;
    }

}
