package org.example.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    void testValidPassword() {
        assertTrue(validator.isValid("AbTp9!fok"));
    }

    @Test
    void testPasswordTooShort() {
        assertFalse(validator.isValid("AbTp9!fo"));
    }

    @Test
    void testPasswordWithoutNumber() {
        assertFalse(validator.isValid("AbTp!foka"));
    }

    @Test
    void testPasswordWithoutLowercase() {
        assertFalse(validator.isValid("ABTP9!FOK"));
    }

    @Test
    void testPasswordWithoutUppercase() {
        assertFalse(validator.isValid("abtp9!fok"));
    }

    @Test
    void testPasswordWithoutSpecialCharacter() {
        assertFalse(validator.isValid("AbTp9Fok"));
    }

    @Test
    void testPasswordWithRepeatedCharacters() {
        assertFalse(validator.isValid("AbTp9!foo"));
    }

    @Test
    void testPasswordWithSpaces() {
        assertFalse(validator.isValid("AbTp9! fok"));
    }

    @Test
    void testEmptyPassword() {
        assertFalse(validator.isValid(""));
    }

    @Test
    void testPasswordWithNull() {
        assertFalse(validator.isValid(null));
    }
}
