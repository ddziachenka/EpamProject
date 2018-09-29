package by.gsu.epamlab.service.dao.impl;

import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.dao.IUserDAO;
import by.gsu.epamlab.service.exception.UserException;

import java.util.HashMap;
import java.util.Map;

import static by.gsu.epamlab.Constants.WRONG_LOGIN;
import static by.gsu.epamlab.Constants.WRONG_LOGIN_OR_PASSWORD;


public class UserMemoryImpl implements IUserDAO {
    private static final Map<String, String> USERS = new HashMap<>();

    @Override
    public User getUser(String login, String password) throws UserException {
        if (!password.equals(USERS.get(login))) {
            throw new UserException(WRONG_LOGIN_OR_PASSWORD);
        }
        return new User(login, password);
    }

    @Override
    public User addUser(String login, String password) throws UserException {
        synchronized (UserMemoryImpl.class) {
            if (USERS.containsKey(login)) {
                throw new UserException(WRONG_LOGIN);
            } else {
                USERS.put(login, password);
            }
        }
        return new User(login, password);
    }
}
