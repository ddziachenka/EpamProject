package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.service.factory.SectionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = "/list/BucketSection", loadOnStartup = 1)
public class BucketSectionController extends AbstractSectionController {

    @Override
    public void init() {
        section = SectionFactory.getBucketSection();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        req.getRequestDispatcher("/list/file").forward(req, resp);
    }
}
