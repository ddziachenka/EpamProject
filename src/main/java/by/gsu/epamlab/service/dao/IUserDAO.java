package by.gsu.epamlab.service.dao;


import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.exception.DAOException;
import by.gsu.epamlab.service.exception.UserException;

public interface IUserDAO {
    User getUser(String login, String password) throws DAOException, UserException;

    User addUser(String login, String password) throws DAOException, UserException;
}

