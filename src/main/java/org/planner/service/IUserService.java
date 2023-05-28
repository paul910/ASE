package org.planner.service;

import org.planner.domain.User;

public interface IUserService {
    User createUser(String username, String password, String emailAddress);
    void removeUserByEMail(String emailAddress);
    void removeUserByUsername(String username);
    User login(String username, String password);
    boolean isValidEmail(String emailAddress);
    boolean doesUserExistByUsername(String username);
    boolean doesUserExistByEmail(String emailAddress);
}