package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.MyValidator;
import org.springframework.beans.factory.annotation.Value;

public class BeanB implements MyValidator {
    @Value("${beanB.name}")
    private String name;
    @Value("${beanB.value}")
    private int value;

    @Override
    public String toString() {
        return name + ' ' + value;
    }

    private void customInitMethod() {
        System.out.println("custom init beanB method");
    }

    private void anotherCustomInitMethod() {
        System.out.println("Another custom init beanB method");
    }

    private void customDestroyMethod() {
        System.out.println("custom destroy beanB method");
    }

    @Override
    public void validate() {
        if (name != null && value > 0) {
            System.out.println("bean "+this.getClass()+" is valid");
        } else {
            System.out.println("bean "+this.getClass()+" is NOT valid");
        }
    }
}
