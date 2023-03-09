package org.planner.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.planner.domain.User;

public class UserServiceTest {
    private static final String ROOT_PATH = "src/test/resources";
    private static final String FILE_PATH = ROOT_PATH + "/users.csv";
    private UserService userService;
    private User user1;

    @BeforeEach
    public void setUp() throws IOException, NoSuchAlgorithmException {
        this.userService = new UserService(ROOT_PATH);
        this.user1 = userService.createUser("user1", "password", "user1@example.com");
    }

    @AfterEach
    public void tearDown() {
        File file = new File(FILE_PATH);
        file.delete();
    }

    @Test
    public void testCreateUser() throws NoSuchAlgorithmException, IOException {
        // Test creating a user with valid credentials
        assertNotNull(this.user1);
        assertEquals("user1", user1.getUsername());
        assertEquals("user1@example.com", user1.getEmail());

        // Test creating a user with a taken username
        User user2 = userService.createUser("user1", "password", "user2@example.com");
        assertNull(user2);

        // Test creating a user with an invalid email address
        User user3 = userService.createUser("user3", "password", "invalid-email-address");
        assertNull(user3);
    }

    @Test
    public void testRemoveUserByEMail() throws NoSuchAlgorithmException, IOException {
        // Test removing an existing user by email
        this.userService.createUser("user1", "password", "user1@example.com");
        this.userService.removeUserByEMail("user1@example.com");
        assertFalse(this.userService.doesUserExistByEmail("user1@example.com"));

        // Test removing a non-existing user by email
        this.userService.removeUserByEMail("non-existing-user@example.com");
        assertFalse(this.userService.doesUserExistByEmail("non-existing-user@example.com"));
    }

    @Test
    public void testRemoveUserByUsername() throws NoSuchAlgorithmException, IOException {
        // Test removing an existing user by username
        this.userService.createUser("user1", "password", "user1@example.com");
        this.userService.removeUserByUsername("user1");
        assertTrue(this.userService.doesUserExistByUsername("user1"));

        // Test removing a non-existing user by username
        this.userService.removeUserByUsername("non-existing-user");
        assertFalse(this.userService.doesUserExistByUsername("non-existing-user"));
    }

    @Test
    public void testLogin() throws NoSuchAlgorithmException, IOException {
        // Test logging in with valid credentials
        User loggedInUser = this.userService.login("user1", "password");
        assertNotNull(loggedInUser);
        assertEquals("user1", loggedInUser.getUsername());
        assertEquals("user1@example.com", loggedInUser.getEmail());

        // Test logging in with incorrect password
        User loggedInUser2 = this.userService.login("user1", "wrong-password");
        assertNull(loggedInUser2);

        // Test logging in with non-existing username
        User loggedInUser3 = this.userService.login("non-existing-user", "password");
        assertNull(loggedInUser3);
    }

    @Test
    public void testIsValidEmail() {
        // Test with valid email address
        assertTrue(this.userService.isValidEmail("user1@example.com"));

        // Test with invalid email address
        assertFalse(this.userService.isValidEmail("invalid-email-address"));
    }
}

