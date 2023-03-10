package org.planner.persistence;

import org.planner.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface<User> {
    private final String filePath;
    private final File file;

    public UserRepository() {
        this.filePath = ROOT_PATH + "\\" + User.class.getName() + "" + FILE_EXTENSION;
        this.file = new File(this.filePath);

        createFolderIfNotExists();
        createFileIfNotExists();
    }

    private void createFolderIfNotExists() {
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }
    }

    private void createFileIfNotExists() {
        if (!this.file.exists()) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(filePath);
                writer.append(User.getColumns());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void persistList(List<User> users) {
        List<User> usersToSave = filterExisting(users);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (User user : usersToSave) {
                bw.write("\n" + user.toString());
            }
        } catch (IOException e) {
            this.LOGGER.info("User list could not be persisted.");
        }
        this.LOGGER.info("User list persisted successfully.");
    }

    @Override
    public List<User> loadList() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                User user = new User(data[0], data[1], data[2]);
                users.add(user);
            }
            this.LOGGER.info("User list loaded successfully.");
        } catch (IOException e) {
            this.LOGGER.info("User list could not be loaded.");
        }
        return users;
    }

    @Override
    public void deleteList() {
        this.file.delete();
        LOGGER.info("User list deleted successfully.");
    }

    @Override
    public List<User> filterExisting(List<User> users) {
        List<User> usersToSave = new ArrayList<>();
        for (User user : users) {
            if (!existsInFile(user)) {
                usersToSave.add(user);
            }
        }
        LOGGER.info("User list filtered successfully.");
        return usersToSave;
    }

    @Override
    public boolean existsInFile(User user) {
        List<User> users = loadList();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                LOGGER.info("User already exists in file.");
                return true;
            }
        }
        return false;
    }
}