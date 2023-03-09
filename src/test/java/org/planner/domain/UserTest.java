package org.planner.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGetUsername() {
        User user = new User("john_doe", "password123", "john_doe@example.com");
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User("john_doe", "password123", "john_doe@example.com");
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetEmail() {
        User user = new User("john_doe", "password123", "john_doe@example.com");
        assertEquals("john_doe@example.com", user.getEmail());
    }

    @Test
    public void testGetColums() {
        assertEquals("Username,Password,Email", User.getColums());
    }
}
