package org.planner.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.planner.domain.User;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    private User user1;

    @BeforeEach
    public void setUp() {
        this.userService = new UserService();
        this.user1 = userService.createUser("user12", "password", "user12@example.com");
    }

    @Test
    public void testRemoveUserByEMail() {
        // Test removing an existing user by email
        this.userService.createUser("user12", "password", "user12@example.com");
        this.userService.removeUserByEMail("user12@example.com");
        assertFalse(this.userService.doesUserExistByEmail("user12@example.com"));

        // Test removing a non-existing user by email
        this.userService.removeUserByEMail("non-existing-user@example.com");
        assertFalse(this.userService.doesUserExistByEmail("non-existing-user@example.com"));
    }

    @Test
    public void testRemoveUserByUsername() {
        // Test removing an existing user by username
        this.userService.createUser("user12", "password", "user12@example.com");
        this.userService.removeUserByUsername("user12");
        assertTrue(this.userService.doesUserExistByUsername("user12"));

        // Test removing a non-existing user by username
        this.userService.removeUserByUsername("non-existing-user");
        assertFalse(this.userService.doesUserExistByUsername("non-existing-user"));
    }

    @Test
    public void testLogin() {
        // Test logging in with valid credentials
        User loggedInUser = this.userService.login("user12", "password");
        assertNotNull(loggedInUser);
        assertEquals("user12", loggedInUser.getUsername());
        assertEquals("user12@example.com", loggedInUser.getEmail());

        // Test logging in with incorrect password
        User loggedInUser2 = this.userService.login("user12", "wrong-password");
        assertNull(loggedInUser2);

        // Test logging in with non-existing username
        User loggedInUser3 = this.userService.login("non-existing-user", "password");
        assertNull(loggedInUser3);
    }

    @Test
    public void testIsValidEmail() {
        // Test with valid email address
        assertTrue(this.userService.isValidEmail("user12@example.com"));

        // Test with invalid email address
        assertFalse(this.userService.isValidEmail("invalid-email-address"));
    }
}

