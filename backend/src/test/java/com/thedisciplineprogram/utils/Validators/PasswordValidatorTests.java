package com.thedisciplineprogram.utils.Validators;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.thedisciplineprogram.utils.Validators.isValidPassword;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTests {
    List<String> disallowedCharacters = List.of("\"", "'", "`", "\\", "/", "<", ">", "{", "}", "[", "]", "(", ")", ";");
    List<String> allowedCharacters = List.of("!" ,"@", "#", "$", "%", "^", "&", "*", "-", "_", "=", "+", "|", ",", ".", "?");

    @Test
    void testValidPasswordIsAccepted() {
        String randomAllowedChar = allowedCharacters.get(ThreadLocalRandom.current().nextInt(allowedCharacters.size()));
        String validPassword = "Test123" + randomAllowedChar;
        assertTrue(isValidPassword(validPassword), "Valid password \"" + validPassword + "\" should be accepted");
    }

    @Test
    void testShortPasswordIsNotAccepted() {
        assertFalse(isValidPassword("Te12!"), "Short password should not be accepted");
    }

    @Test
    void testLongPasswordIsNotAccepted() {
        assertFalse(isValidPassword("Test1234567891234567891234567891!"), "Long password should not be accepted");
    }

    @Test
    void testPasswordWithoutUppercaseIsNotAccepted() {
        assertFalse(isValidPassword("test123!"), "Password without uppercase should not be accepted");
    }

    @Test
    void testPasswordWithoutLowercaseIsNotAccepted() {
        assertFalse(isValidPassword("TEST123!"), "Password without lower case should not be accepted");
    }

    @Test
    void testPasswordWithoutSpecialCharactersIsNotAccepted() {
        assertFalse(isValidPassword("Test123"), "Password without special characters should not be accepted");
    }

    @Test
    void testWithDisallowedCharacterIsNotAccepted() {
        String randomDisallowedChar = disallowedCharacters.get(ThreadLocalRandom.current().nextInt(disallowedCharacters.size()));
        String inValidCharacterPassword = "Test123" + randomDisallowedChar;
        assertFalse(isValidPassword(inValidCharacterPassword), "Password with invalid character \"" + inValidCharacterPassword + "\" should not be accepted");
    }
}
