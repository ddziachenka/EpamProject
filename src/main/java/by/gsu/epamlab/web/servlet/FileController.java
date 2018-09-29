package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.service.exception.DAOException;
import by.gsu.epamlab.service.file.FileManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;


@WebServlet(value = "/list/file", loadOnStartup = 1)
@MultipartConfig
public class FileController extends AbstractController {
    private static final Logger LOGGER = Logger.getLogger(FileController.class.getName());

    private FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(getServletContext().getRealPath(WEB_INF_PATH));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        fileManager.getFile(req.getParameter(ID), getServletContext(), resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part file = req.getPart(FILE);
        String id = req.getAttribute(ID).toString();
        if (file != null && file.getSize() > 0) {
            fileManager.saveFile(file, id);
        }
        resp.sendRedirect(req.getAttribute(FORWARD_REQUEST_URI).toString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            fileManager.deleteFile(req.getParameter(ID));
        } catch (DAOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}


