package org.planner.service;

import org.planner.Debug;
import org.planner.domain.User;

import java.util.logging.Logger;

public class UserServiceDecorator implements IUserService{

    private final Logger logger;
    private final IUserService userService;

    public UserServiceDecorator(IUserService userService) {
        this.userService = userService;
        this.logger = Logger.getLogger(UserServiceDecorator.class.getName());
        logger.setLevel(Debug.logLevel);
    }

    @Override
    public User createUser(String username, String password, String emailAddress) {
        return userService.createUser(username, password, emailAddress);
    }

    @Override
    public void removeUserByEMail(String emailAddress) {
        userService.removeUserByEMail(emailAddress);
    }

    @Override
    public void removeUserByUsername(String username) {
        userService.removeUserByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        return userService.login(username, password);
    }

    @Override
    public boolean isValidEmail(String emailAddress) {
        return userService.isValidEmail(emailAddress);
    }

    @Override
    public boolean doesUserExistByUsername(String username) {
        return userService.doesUserExistByUsername(username);
    }

    @Override
    public boolean doesUserExistByEmail(String emailAddress) {
        return userService.doesUserExistByEmail(emailAddress);
    }
}
