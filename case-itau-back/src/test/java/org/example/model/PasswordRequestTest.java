package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordRequestTest {

    @Test
    void testGetPassword() {
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setPassword("myPassword");
        assertEquals("myPassword", passwordRequest.getPassword());
    }

    @Test
    void testSetPassword() {
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setPassword("newPassword");
        assertEquals("newPassword", passwordRequest.getPassword());
    }

    @Test
    void testPasswordIsNullByDefault() {
        PasswordRequest passwordRequest = new PasswordRequest();
        assertNull(passwordRequest.getPassword());
    }

    @Test
    void testSetPasswordToNull() {
        PasswordRequest passwordRequest = new PasswordRequest();
        passwordRequest.setPassword(null);
        assertNull(passwordRequest.getPassword());
    }
}
