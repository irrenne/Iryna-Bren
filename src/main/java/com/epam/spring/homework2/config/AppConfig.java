package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.context.annotation.*;

@Configuration
@Import(AppConfigProcessors.class)
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Bean("beanA")
    public BeanA getBeanA() {
        return new BeanA();
    }

    @Bean(value = "beanB", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanD")
    public BeanB getBeanB() {
        return new BeanB();
    }

    @Bean(value = "beanC", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC getBeanC() {
        return new BeanC();
    }

    @Bean(value = "beanD", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD getBeanD() {
        return new BeanD();
    }

    @Bean("beanE")
    public BeanE getBeanE() {
        return new BeanE();
    }

    @Bean("beanF")
    @Lazy
    public BeanF getBeanF() {
        return new BeanF();
    }
}
