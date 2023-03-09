package org.planner.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Logger;

import org.planner.domain.User;

public class UserRepository {
    private Logger logger;
    private String filePath;
    private File file;
    private String header;

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