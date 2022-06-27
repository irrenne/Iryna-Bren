package com.epam.spring.homework2.config;

import com.epam.spring.homework2.bfpp.MyBeanFactoryPostProcessor;
import com.epam.spring.homework2.bpp.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigProcessors {

    @Bean
    public static MyBeanPostProcessor getMyBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    public static MyBeanFactoryPostProcessor getMyBeanFactoryPostProcessor() {
        return new MyBeanFactoryPostProcessor();
    }
}
