package com.thedisciplineprogram.utils.validators;

import com.thedisciplineprogram.utils.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        List<String> errors = new ArrayList<>();

        if (password.length() < 6 || password.length() > 32) {
            errors.add("Password must be between 6 and 32 characters");
        }

        if (password.matches(".*[\"'`\\\\/<>{}\\[\\]();:].*")) {
            errors.add("Password must not contain disallowed symbols");
        }

        if (!password.matches(".*[0-9].*")) {
            errors.add("Password must contain at least one digit");
        }

        if (!password.matches(".*[a-z].*")) {
            errors.add("Password must contain at least one lowercase letter");
        }

        if (!password.matches(".*[A-Z].*")) {
            errors.add("Password must contain at least one uppercase letter");
        }

        if (!password.matches(".*[@#$%^&+=!_.|?*\\-,].*")) {
            errors.add("Password must contain at least one special character (@#$%^&+=!)");
        }

        if (password.matches(".*\\s.*")) {
            errors.add("Password must not contain whitespace");
        }

        if (!errors.isEmpty()) {
            context.disableDefaultConstraintViolation();
            for (String error : errors) {
                context.buildConstraintViolationWithTemplate(error)
                        .addConstraintViolation();
            }
            return false;
        }

        return true;
    }
}
