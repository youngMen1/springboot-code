package com.seal.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/8/13 13:57
 * @description
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.seal.swagger.controller"))
                //.paths(AppUtility.isProd() ? PathSelectors.none() : PathSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试swagger")
                .description("展示swagger界面")
                .termsOfServiceUrl("http://localhost:8089/swagger-ui.html")
                .contact(new Contact("seal", "http://localhost:8089/swagger-ui.html", "xxxx"))
                .version("1.0")
                .build();
    }
}
