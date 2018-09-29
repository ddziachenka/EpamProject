package by.gsu.epamlab.service.section;

import by.gsu.epamlab.model.Task;
import by.gsu.epamlab.service.database.DBManager;
import by.gsu.epamlab.service.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;

public class FixSection extends AbstractSection {
    private static final Logger LOGGER = Logger.getLogger(FixSection.class.getName());

    protected final String getTaskQuery;
    protected final String deleteTaskQuery;

    public FixSection(String getTaskQuery, String deleteTaskQuery) {
        this.getTaskQuery = getTaskQuery;
        this.deleteTaskQuery = deleteTaskQuery;
    }


    @Override
    public List<Task> getTasks(String login) throws DAOException {
        try (Connection connection = DBManager.getConnectionDB();
             PreparedStatement preparedStatement = connection.prepareStatement(getTaskQuery)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getInt(ID),
                        LocalDate.parse(resultSet.getDate(DATE).toString()),
                        resultSet.getString(DESCRIPTION), login,
                        resultSet.getBoolean(FILE)));
            }
            return tasks;
        } catch (DAOException | SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(SERVER_ERROR);
        }
    }

    protected void runQuery(String query, int... tasks) throws DAOException {
        try (Connection connection = DBManager.getConnectionDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int id : tasks) {
                preparedStatement.setInt(1, id);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (DAOException | SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(SERVER_ERROR);
        }
    }

    @Override
    public void deleteTask(int... tasks) throws DAOException {
        runQuery(deleteTaskQuery, tasks);
    }


}
