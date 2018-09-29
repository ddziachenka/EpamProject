package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.service.factory.SectionFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/list/FixSection", loadOnStartup = 1)
public class FixSectionController extends AbstractSectionController {

    @Override
    public void init() {
        section = SectionFactory.getFixSection();
    }
}
