package org.planner.persistence;

import org.planner.Debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class Repository {
    static Logger logger = Logger.getLogger(Repository.class.getName());
    protected static final String ROOT_PATH = "src/main/resources/";
    protected static final String FILE_EXTENSION = ".csv";
    final String filePath;
    final File file;
    final String columns;

    public Repository(String className, String columns) {
        logger = Logger.getLogger(className);
        logger.setLevel(Debug.logLevel);

        this.filePath = ROOT_PATH + className + FILE_EXTENSION;
        this.file = new File(this.filePath);
        this.columns = columns;

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
                writer.append(this.columns);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteFile() {
        this.file.delete();
        createFileIfNotExists();
        logger.info("Activity list deleted successfully.");
    }
}
