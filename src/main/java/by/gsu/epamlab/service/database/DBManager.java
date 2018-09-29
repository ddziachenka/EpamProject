package by.gsu.epamlab.service.database;

import by.gsu.epamlab.service.dao.impl.UserDBImpl;
import by.gsu.epamlab.service.exception.DAOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.JDBC_OBJECT;
import static by.gsu.epamlab.Constants.NODE_JNDI;
import static by.gsu.epamlab.Constants.SERVER_ERROR;

public class DBManager {
    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());

    public static Connection getConnectionDB() throws DAOException {
        try {
            Context envCtx = (Context) (new InitialContext().lookup(NODE_JNDI));
            DataSource ds = (DataSource) envCtx.lookup(JDBC_OBJECT);
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DAOException(SERVER_ERROR);
        }
    }
}


