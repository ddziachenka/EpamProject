package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.dao.IUserDAO;
import by.gsu.epamlab.service.exception.DAOException;
import by.gsu.epamlab.service.exception.UserException;
import by.gsu.epamlab.service.factory.UserFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;


@WebServlet(value = "/UserController", loadOnStartup = 1)
public class UserController extends AbstractController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    private IUserDAO userDAO;

    @Override
    public void init() {
        userDAO = UserFactory.getClassFromFactory(getServletContext().getInitParameter(IMPLEMENTATION));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (!checkInputData(login, password)) {
            request.setAttribute(ERROR, EMPTY_LOGIN_OR_PASSWORD);
            RequestDispatcher rd = request.getRequestDispatcher(INDEX_JSP);
            rd.forward(request, response);
        } else {
            try {
                User user = checkUserRegister(login, password, userDAO);
                request.getSession().setAttribute(USER, user);
                jump(request, response);
            } catch (DAOException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            } catch (UserException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            }
        }
    }

    protected void jump(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(SLASH);
    }

    protected User checkUserLoginIn(String login, String password, IUserDAO userDAO) throws DAOException, UserException {
        return userDAO.getUser(login, password);
    }

    protected boolean checkInputData(String login, String password) {
        if (login.isEmpty() || password.isEmpty() || EMPTY.equals(login.trim())) {
            return false;
        }
        return true;
    }

    private User checkUserRegister(String login, String password, IUserDAO userDAO) throws DAOException, UserException {
        return userDAO.addUser(login, password);
    }

}


