package com.epam.spring.homework.project.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword contactNumber) {
    }

    @Override
    public boolean isValid(String passwordField,
                           ConstraintValidatorContext cxt) {
        return passwordField.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,32}$");
    }
}