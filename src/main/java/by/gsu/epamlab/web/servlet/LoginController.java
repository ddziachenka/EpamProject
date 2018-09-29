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


@WebServlet(value = "/LoginController", loadOnStartup = 1)
public class LoginController extends UserController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

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
                User user = checkUserLoginIn(login, password, userDAO);
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(INDEX_JSP);
    }


}





