//package com.wzz.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
///**
// * @author by
// * @implNote 2023/4/10 9:43
// */
//@Configuration //在springboot中加载配置文件
//public class SwaggerConfig {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo()) // 调用apiInfo方法
//                .pathMapping("/") //配置访问路径
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.wzz.controller"))
//                .paths(PathSelectors.regex("/.*")) //匹配路径下的方法
//                .build();
//
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("领航培训管理系统文档")
//                .contact(new Contact("gitee地址", "https://gitee.com/wzhouzhou/exam_system", "1206414629@qq.com"))
//                .description("领航培训管理系统集成swaggerUi生成的接口文档")
//                .version("1.0")
//                .build();
//    }
//}
