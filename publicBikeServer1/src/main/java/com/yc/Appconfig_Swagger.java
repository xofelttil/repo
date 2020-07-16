//package com.yc;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import io.swagger.models.Contact;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
////@Configuration
////@EnableSwagger2
//public class Appconfig_Swagger {
//
//	//@Value(value="${swagger.enabled}")
//	Boolean swaggerEnabled;
//	
//	@Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                // 是否开启
//                .enable(swaggerEnabled).select()
//                // 扫描的路径包,只要这些包中的类配有swagger注解，则启用这些注解
//                .apis(RequestHandlerSelectors.basePackage("com"))
//                // 指定路径处理PathSelectors.any()代表所有的路径
//                .paths(     PathSelectors.any()     ).build().pathMapping("/");
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("卢本伟单车接口")
//                .description("springboot | swagger")
//                .version("1.0.0")
//                .build();
//    }
////	//@Bean
////	public  DriverManagerDataSource   mysqlDataSource() {
////		DriverManagerDataSource dataSource = new DriverManagerDataSource();
////		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
////		dataSource.setUrl("jdbc:mysql://localhost:3306/bank");
////		dataSource.setUsername("root");
////		dataSource.setPassword("a");
////		return dataSource;
////	}
//	
//	
//}