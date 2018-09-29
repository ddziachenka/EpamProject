package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.model.Task;
import by.gsu.epamlab.model.User;
import by.gsu.epamlab.service.exception.DAOException;
import by.gsu.epamlab.service.factory.SectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;


@WebServlet(value = "/list/SomeDaySection", loadOnStartup = 1)
@MultipartConfig

public class SomeDaySectionController extends AbstractSectionController {
    private static final Logger LOGGER = Logger.getLogger(SomeDaySectionController.class.getName());

    @Override
    public void init() {
        section = SectionFactory.getSomeDaySection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter(DESCRIPTION);
        String date = req.getParameter(DATE);
        String login = ((User) req.getSession().getAttribute(USER)).getLogin();

        Part file = req.getPart(FILE);
        boolean hasFile = file != null && file.getSize() > 0;
        try {
            if (date != null) {
                req.setAttribute(ID, section.addTask(new Task(description, login, hasFile, LocalDate.parse(date))));
            } else {
                req.setAttribute(ID, section.addTask(new Task(description, login, hasFile)));
            }
        } catch (DAOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        req.getRequestDispatcher("/list/file").forward(req, resp);

    }


}
