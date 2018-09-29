package by.gsu.epamlab.service.factory;

import by.gsu.epamlab.service.dao.IUserDAO;
import by.gsu.epamlab.service.dao.impl.UserDBImpl;
import by.gsu.epamlab.service.dao.impl.UserMemoryImpl;

import static by.gsu.epamlab.Constants.DATABASE;

public class UserFactory {

    public static IUserDAO getClassFromFactory(String impl) {
        if (impl.equals(DATABASE)) {
            return new UserDBImpl();
        } else return new UserMemoryImpl();
    }

}
