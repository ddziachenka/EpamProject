package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.exception.DAOException;
import by.gsu.epamlab.service.section.AbstractSection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;


public class AbstractSectionController extends AbstractController {
    private static final Logger LOGGER = Logger.getLogger(AbstractSectionController.class.getName());

    protected AbstractSection section;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute(TASKS, section.getTasks(((User) req.getSession().getAttribute(USER)).getLogin()));
        } catch (DAOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        req.setAttribute(SECTION, section.toString());
        RequestDispatcher rd = req.getRequestDispatcher(LIST_JSP);
        rd.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            section.changeTask(getArrayTasks(req));
        } catch (DAOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            section.deleteTask(getArrayTasks(req));
        } catch (DAOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private int[] getArrayTasks(HttpServletRequest req) {
        String[] id = req.getParameterValues(ID);
        int[] ids = new int[id.length];
        for (int i = 0; i < id.length; i++) {
            ids[i] = Integer.parseInt(id[i]);
        }
        return ids;
    }
}



