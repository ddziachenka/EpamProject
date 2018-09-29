package by.gsu.epamlab.service.section;

import by.gsu.epamlab.model.Task;
import by.gsu.epamlab.service.database.DBManager;
import by.gsu.epamlab.service.exception.DAOException;

import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.SERVER_ERROR;


public class SomeDaySection extends BucketSection {
    private static final Logger LOGGER = Logger.getLogger(SomeDaySection.class.getName());

    protected final String addQuery;

    public SomeDaySection(String getTaskQuery, String deleteTaskQuery, String changeQuery, String addQuery) {
        super(getTaskQuery, deleteTaskQuery, changeQuery);
        this.addQuery = addQuery;
    }

    @Override
    public int addTask(Task task) throws DAOException {
        try (Connection connection = DBManager.getConnectionDB();
             PreparedStatement preparedStatement = connection.prepareStatement(addQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getLogin());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setBoolean(3, task.isHasFile());
            preparedStatement.setDate(4, Date.valueOf(task.getDate()), Calendar.getInstance());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(SERVER_ERROR);
        }
    }
}



