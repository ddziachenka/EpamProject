package by.gsu.epamlab.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.Constants.*;

public abstract class AbstractController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter(METHOD);
        if (method != null) {
            if (method.equals(DELETE)) {
                doDelete(req, resp);
            } else if (method.equals(PUT)) {
                doPut(req, resp);
            }
        } else {
            super.service(req, resp);
        }

    }


}

