package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.MyValidator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE implements MyValidator {
    private String name;
    private int value;

    @Override
    public String toString() {
        return name + ' ' + value;
    }

    @PostConstruct
    public void beanEInit() {
        System.out.println("BeanE init (@PostConstruct)");
    }

    @PreDestroy
    public void beanEDestroy() {
        System.out.println("BeanE destroy (@PreDestroy)");
    }

    @Override
    public void validate() {
        if (name != null && value > 0) {
            System.out.println("bean " + this.getClass() + " is valid");
        } else {
            System.out.println("bean " + this.getClass() + " is NOT valid");
        }
    }
}
