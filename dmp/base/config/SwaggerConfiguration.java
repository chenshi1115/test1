package com.yuanian.dmp.base.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.yuanian.common.config.SwaggerApiInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 访客
 * @since Wed Dec 30 14:46:58 CST 2020
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

// 定义分隔符
private static final String splitor=";";


@Bean(value = "microApi")
@Order(value = 1)
public Docket microRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(groupApiInfo())
        .groupName("micro-doc")
        .select()
        .apis(SwaggerApiInfo.basePackage("com.yuanian.base.client"+splitor+"com.yuanian.base.controller"))
        .paths(PathSelectors.any())
        .build();
        }


private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
        .title("swagger-bootstrap-ui很棒~~~！！！")
        .description("<div style='font-size:14px;color:red;'>swagger-bootstrap-ui-demo RESTful APIs</div>")
        .termsOfServiceUrl("http://www.group.com/")
        .contact("group@qq.com")
        .version("1.0")
        .build();
        }
        }
