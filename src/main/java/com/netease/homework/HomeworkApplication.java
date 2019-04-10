package com.netease.homework;

import com.netease.homework.storage.StorageProperties;
import com.netease.homework.storage.StorageService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
@MapperScan("com.netease.homework.repository")
@EnableConfigurationProperties(StorageProperties.class)
public class HomeworkApplication extends WebMvcConfigurerAdapter {

    //为了解决静态资源无法访问的问题
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            deleteAll()函数慎用！会把之前上传的文件全部删除！
//            storageService.deleteAll();
            storageService.init();
        };
    }

}

