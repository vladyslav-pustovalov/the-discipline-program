package com.thedisciplineprogram.utils.validators;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordValidatorTests {
    private PasswordValidator passwordValidator;
    private ConstraintValidatorContext validatorContext;

    @BeforeEach
    public void setUp() {
        passwordValidator = new PasswordValidator();
        validatorContext = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(validatorContext.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addConstraintViolation()).thenReturn(validatorContext);
    }

    @ParameterizedTest
    @ValueSource(strings = {"!" ,"@", "#", "$", "%", "^", "&", "*", "-", "_", "=", "+", "|", ",", ".", "?"})
    void testValidPasswordIsAccepted(String allowedCharacter) {
        String validPassword = "Test123" + allowedCharacter;
        assertTrue(passwordValidator.isValid(validPassword, validatorContext), "Valid password \"" + validPassword + "\" should be accepted");
    }

    @Test
    void testShortPasswordIsNotAccepted() {
        assertFalse(passwordValidator.isValid("Te12!", validatorContext), "Short password should not be accepted");
    }

    @Test
    void testLongPasswordIsNotAccepted() {
        assertFalse(passwordValidator.isValid("Test1234567891234567891234567891!", validatorContext), "Long password should not be accepted");
    }

    @Test
    void testPasswordWithoutUppercaseIsNotAccepted() {
        assertFalse(passwordValidator.isValid("test123!", validatorContext), "Password without uppercase should not be accepted");
    }

    @Test
    void testPasswordWithoutLowercaseIsNotAccepted() {
        assertFalse(passwordValidator.isValid("TEST123!", validatorContext), "Password without lower case should not be accepted");
    }

    @Test
    void testPasswordWithoutSpecialCharactersIsNotAccepted() {
        assertFalse(passwordValidator.isValid("Test123", validatorContext), "Password without special characters should not be accepted");
    }

    @ParameterizedTest
    @ValueSource(strings = {"\"", "'", "`", "\\", "/", "<", ">", "{", "}", "[", "]", "(", ")", ";"})
    void testWithDisallowedCharacterIsNotAccepted(String disallowedCharacter) {
        String inValidCharacterPassword = "Test123" + disallowedCharacter;
        assertFalse(passwordValidator.isValid(inValidCharacterPassword, validatorContext), "Password with invalid character \"" + inValidCharacterPassword + "\" should not be accepted");
    }
}
