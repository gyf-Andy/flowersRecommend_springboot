package com.flower.flower2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.flower")//看这个包下的类有没有bean注解    描到有@Component、@Controller、@Service等这些注解的类，并注册为Bean，
@MapperScan("com.flower.mapper") //使com.gyf.mapper生成代理
public class Flower2Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Flower2Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Flower2Application.class, args);
    }

}
