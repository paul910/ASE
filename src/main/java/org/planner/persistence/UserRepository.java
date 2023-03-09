package org.planner.persistence;

import org.planner.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository {
    private final Logger logger;
    private final String filePath;
    private final File file;
    private final String header;

    public UserRepository(String rootPath) {
        this.logger = Logger.getLogger(UserRepository.class.getName());
        this.filePath = rootPath + "\\users.csv";
        this.file = new File(this.filePath);
        this.header = User.getColums();

        File root = new File(rootPath);
        if (!root.exists()) root.mkdir();
        if (!this.file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void persistUserList(List<User> users) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(this.filePath));
            writer.write(this.header + "\n");
            for (User user : users) {
                writer.write(user.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            this.logger.info("User list could not be persisted.");
        }
        this.logger.info("User list persisted successfully.");
    }

    public List<User> loadUserList() {
        List<User> users = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));

            // skip the first line (header)
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = fields[0];
                String password = fields[1];
                String emailAddress = fields[2];
                users.add(new User(username, password, emailAddress));
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.logger.info("User list loaded successfully.");
        return users;
    }
}