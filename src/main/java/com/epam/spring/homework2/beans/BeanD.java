package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.MyValidator;

public class BeanD implements MyValidator {
    private final String name;
    private final int value;

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ' ' + value;
    }

    private void customInitMethod() {
        System.out.println("custom init beanD method");
    }

    private void customDestroyMethod() {
        System.out.println("custom destroy beanD method");
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
