package by.gsu.epamlab.service.dao.impl;

import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.dao.IUserDAO;
import by.gsu.epamlab.service.database.DBManager;
import by.gsu.epamlab.service.exception.DAOException;
import by.gsu.epamlab.service.exception.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.SERVER_ERROR;
import static by.gsu.epamlab.Constants.WRONG_LOGIN;

public class UserDBImpl implements IUserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDBImpl.class.getName());

    private final static String GET_USER = "SELECT login, password FROM users WHERE login = ? AND password = ?";
    private final static String ADD_USER = "INSERT INTO  users (login, password) SELECT ?, ? FROM DUAL " +
            "WHERE NOT EXISTS (SELECT login, password FROM users WHERE login = ?)";


    @Override
    public User getUser(String login, String password) throws DAOException, UserException {
        try (Connection connection = DBManager.getConnectionDB();
             PreparedStatement statement = connection.prepareStatement(GET_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new User(result.getString(1), result.getString(2));
            } else {
                throw new UserException(WRONG_LOGIN);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(SERVER_ERROR);
        }
    }

    @Override
    public User addUser(String login, String password) throws DAOException, UserException {

        try (Connection connection = DBManager.getConnectionDB();
             PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, login);
            if (statement.executeUpdate() == 1) {
                return new User(login, password);
            } else {
                throw new UserException(WRONG_LOGIN);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(SERVER_ERROR);
        }


    }

}


