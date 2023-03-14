package org.planner.persistence;

import org.planner.domain.Activity;
import org.planner.domain.User;
import org.planner.service.ActivityService;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserRepository extends Repository {

    public UserRepository() {
        super(UserRepository.class.getSimpleName(), User.getColumns());
    }

    public void persistUserList(List<User> users) {
        List<User> usersToSave = filterExistingUserOut(users);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (User user : usersToSave) {
                bw.write("\n" + user.toCsv());
            }
        } catch (IOException e) {
            logger.info("User list could not be persisted.");
        }
        logger.info("User list persisted successfully.");
    }

    public List<User> loadUserList() {
        List<User> users = new CopyOnWriteArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                User user = new User(data[0], data[1], data[2]);
                users.add(user);
            }
            logger.info("User list loaded successfully.");
        } catch (IOException e) {
            logger.info("User list could not be loaded.");
        }
        return users;
    }

    public List<User> filterExistingUserOut(List<User> users) {
        List<User> usersToSave = new CopyOnWriteArrayList<>();
        for (User user : users) {
            if (!existsInFile(user)) {
                usersToSave.add(user);
            }
        }
        logger.info("User list filtered successfully.");
        return usersToSave;
    }

    public boolean existsInFile(User user) {
        List<User> users = loadUserList();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                logger.info("User already exists in file.");
                return true;
            }
        }
        return false;
    }

    public void removeUserByUserId(String userId) {
        List<User> users = loadUserList();
        for (User user : users) {
            if (user.getUsername().equals(userId)) {
                users.remove(user);
                // Consistency: remove all travels of this user
                new TravelRepository().removeTravelByUser(user);
                break;
            }
        }
        deleteFile();
        persistUserList(users);
        logger.info("User removed successfully.");
    }
}