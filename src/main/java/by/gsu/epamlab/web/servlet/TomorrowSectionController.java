package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.service.factory.SectionFactory;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/list/TomorrowSection", loadOnStartup = 1)
@MultipartConfig
public class TomorrowSectionController extends SomeDaySectionController {

    @Override
    public void init() {
        section = SectionFactory.getTomorrowSection();
    }
}
