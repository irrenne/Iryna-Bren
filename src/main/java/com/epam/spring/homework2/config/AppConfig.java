package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
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
    public BeanB getBeanB(@Value("${beanB.name}") final String name, @Value("${beanB.value}") final int value) {
        return new BeanB(name, value);
    }

    @Bean(value = "beanC", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC getBeanC(@Value("${beanC.name}") final String name, @Value("${beanC.value}") final int value) {
        return new BeanC(name, value);
    }

    @Bean(value = "beanD", initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD getBeanD(@Value("${beanD.name}") final String name, @Value("${beanD.value}") final int value) {
        return new BeanD(name, value);
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
