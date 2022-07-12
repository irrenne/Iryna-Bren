package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.MyValidator;

public class BeanB implements MyValidator {
    private final String name;
    private final int value;

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

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
            System.out.println("bean " + this.getClass() + " is valid");
        } else {
            System.out.println("bean " + this.getClass() + " is NOT valid");
        }
    }
}
