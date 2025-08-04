package com.thedisciplineprogram.utils.validators;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordValidator {

    private PasswordValidator() {}

    public static boolean isValidPassword(String password) {

        if (password.length() < 6 || password.length() > 32) {
            log.error("Password length should be between 6 and 32");
            return false;
        }

        if (password.matches(".*[\"'`\\\\/<>{}\\[\\]();].*")) {
            log.error("Password contains disallowed symbols");
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            log.error("Password does NOT contain an Uppercase letter");
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            log.error("Password does NOT contain a Lowercase letter");
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            log.error("Password does NOT contain a digit");
            return false;
        }

        if (!password.matches(".*[!@#$%^&*\\-_=+|;:'\",.?/\\\\].*")) {
            log.error("Password does NOT contain a symbol");
            return false;
        }

        return true;
    }
}
