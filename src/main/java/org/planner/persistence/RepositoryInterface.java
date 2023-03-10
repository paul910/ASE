package org.planner.persistence;

import java.util.List;
import java.util.logging.Logger;

public interface RepositoryInterface<T> {

    String ROOT_PATH = "src/main/resources";
    String FILE_EXTENSION = ".csv";
    Logger LOGGER = Logger.getLogger(RepositoryInterface.class.getName());

    void persistList(List<T> list);

    List<T> loadList();

    void deleteList();

    List<T> filterExisting(List<T> list);

    boolean existsInFile(T object);

    void removeEntryById(String id);
}
