package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validation.MyValidator;

public class BeanF implements MyValidator {
    private String name;
    private int value;

    @Override
    public String toString() {
        return name + ' ' + value;
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
