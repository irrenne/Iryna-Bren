package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.MyValidator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA implements InitializingBean, DisposableBean, MyValidator {
    private String name;
    private int value;

    @Override
    public String toString() {
        return name + ' ' + value;
    }

    @Override
    public void destroy() {
        System.out.println("BeanA destroy");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("BeanA init (afterPropertiesSet)");
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
