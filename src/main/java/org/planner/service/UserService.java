package org.planner.service;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.planner.domain.User;
import org.planner.helper.PasswordHasher;
import org.planner.persistence.UserRepository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {
    private Logger logger;
    private UserRepository userRepository;
    private List<User> users;

    public UserService(String rootPath) throws NoSuchAlgorithmException, IOException {
        this.logger = Logger.getLogger(UserService.class.getName());
        this.userRepository = new UserRepository(rootPath);
        this.users = this.userRepository.loadUserList();
    }

    public User createUser(String username, String password, String emailAddress)
            throws IOException, NoSuchAlgorithmException {
        User user = new User(username, PasswordHasher.hashPassword(password), emailAddress);
        if (isUsernameOrEmailTaken(username, emailAddress) || !isValidEmail(emailAddress)) {
            logger.warning("User has not been added.");
            return null;
        }
        this.users.add(user);
        this.userRepository.persistUserList(this.users);
        logger.info("User added successfully.");
        return user;
    }

    public void removeUserByEMail(String emailAddress) throws IOException {
        if (!doesUserExistByEmail(emailAddress)) {
            logger.warning("User has not been removed.");
            return;
        }
        this.users.remove(getUserByEmail(emailAddress));
        this.userRepository.persistUserList(this.users);
        logger.info("User removed successfully.");
    }

    public void removeUserByUsername(String username) throws IOException {
        if (doesUserExistByUsername(username)) {
            logger.warning("User has not been removed.");
            return;
        }
        this.users.remove(getUserByUsername(username));
        this.userRepository.persistUserList(this.users);
        logger.info("User removed successfully.");
    }

    public User login(String username, String password) throws NoSuchAlgorithmException {
        if (doesUserExistByUsername(username)) {
            if (isPasswordCorrect(username, password)) {
                logger.info("Login successful.");
                return getUserByUsername(username);
            } else {
                logger.warning("Login failed. Wrong password.");
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean isPasswordCorrect(String username, String password) throws NoSuchAlgorithmException {
        if (getUserByUsername(username).getPassword().equals(PasswordHasher.hashPassword(password))) {
            logger.info("Password is correct.");
            return true;
        } else {
            logger.warning("Password is incorrect.");
            return false;
        }
    }

    public boolean isValidEmail(String emailAddress) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (Pattern.compile(regex).matcher(emailAddress).matches()) {
            logger.info("Valid email address.");
            return true;
        } else {
            logger.warning("Invalid email address.");
            return false;
        }
    }

    private User getUserByUsername(String username) {
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        logger.info("User not found with username: " + username);
        return null;
    }

    private User getUserByEmail(String emailAddress) {
        for (User user : this.users) {
            if (user.getEmail().equals(emailAddress)) {
                return user;
            }
        }
        logger.info("User not found with email: " + emailAddress);
        return null;
    }

    public boolean doesUserExistByUsername(String username) {
        if (getUserByUsername(username) == null) {
            logger.warning("User not found with username: " + username);
            return false;
        } else {
            logger.info("User found with username: " + username);
            return true;
        }
    }

    public boolean doesUserExistByEmail(String emailAddress) {
        if (getUserByEmail(emailAddress) != null) {
            logger.info("User found with email: " + emailAddress);
            return true;
        } else {
            logger.warning("User not found with email: " + emailAddress);
            return false;
        }
    }

    private boolean isUsernameOrEmailTaken(String username, String emailAddress) {
        for (User existingUser : this.users) {
            if (existingUser.getUsername().equals(username)) {
                logger.warning("User with username " + username + " already exists.");
                return true;
            }
            if (existingUser.getEmail().equals(emailAddress)) {
                logger.warning("User with email " + emailAddress + " already exists.");
                return true;
            }
        }
        return false;
    }
}
