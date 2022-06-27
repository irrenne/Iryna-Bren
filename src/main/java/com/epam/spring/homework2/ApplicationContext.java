package com.epam.spring.homework2;

import com.epam.spring.homework2.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.stream.Stream;

public class ApplicationContext {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);

        context.close();
    }
}
