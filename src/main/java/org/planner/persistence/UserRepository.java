package org.planner.persistence;

import org.planner.domain.User;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository {
    private final Logger logger;
    private final String filePath;
    private final File file;
    private final String header;

    public UserRepository(String rootPath) throws IOException {
        this.logger = Logger.getLogger(UserRepository.class.getName());
        this.filePath = rootPath + "\\users.csv";
        this.file = new File(this.filePath);
        this.header = User.getColums();

        File root = new File(rootPath);
        if (!root.exists())
            root.mkdir();
        if (!this.file.exists())
            file.createNewFile();
    }

    public void persistUserList(List<User> users) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        writer.write(this.header + "\n");
        for (User user : users) {
            writer.write(user.toString() + "\n");
        }
        writer.close();
        this.logger.info("User list persisted successfully.");
    }

    public List<User> loadUserList() throws IOException, NoSuchAlgorithmException {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

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
        this.logger.info("User list loaded successfully.");
        return users;
    }
}