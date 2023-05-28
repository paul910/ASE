package org.planner.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.planner.domain.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {

    private static final String ROOT_PATH = "src/test/resources";
    private static final String FILE_PATH = ROOT_PATH + "/UserRepository.csv";
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void testPersistUserList() {
        List<User> users = new ArrayList<>();
        users.add(new User("john", "password123", "john@example.com"));
        users.add(new User("jane", "password456", "jane@example.com"));
        userRepository.persistUserList(users);
        assertTrue(new File(Repository.ROOT_PATH + "UserRepository" + Repository.FILE_EXTENSION).exists());
    }

    @Test
    public void testLoadUserList() {
        List<User> users = new ArrayList<>();
        users.add(new User("john", "password123", "john@example.com"));
        users.add(new User("jane", "password456", "jane@example.com"));
        userRepository.persistUserList(users);
        List<User> loadedUsers = userRepository.loadUserList();
        assertEquals(2, loadedUsers.size());
        assertEquals(users.get(0).getUsername(), loadedUsers.get(0).getUsername());
        assertEquals(users.get(1).getUsername(), loadedUsers.get(1).getUsername());
    }
}
