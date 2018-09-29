package by.gsu.epamlab.service.section;

import by.gsu.epamlab.model.Task;
import by.gsu.epamlab.service.exception.DAOException;

import java.util.List;

import static by.gsu.epamlab.Constants.EMPTY;
import static by.gsu.epamlab.Constants.SECTION;

public abstract class AbstractSection {

    public List<Task> getTasks(String login) throws DAOException {
        throw new UnsupportedOperationException();
    }

    public void changeTask(int... tasks) throws DAOException {
        throw new UnsupportedOperationException();
    }

    public void deleteTask(int... tasks) throws DAOException {
        throw new UnsupportedOperationException();
    }

    public int addTask(Task task) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().replace(SECTION, EMPTY).toUpperCase();
    }
}
