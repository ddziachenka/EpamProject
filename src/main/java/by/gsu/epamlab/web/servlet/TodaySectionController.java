package by.gsu.epamlab.web.servlet;

import by.gsu.epamlab.service.factory.SectionFactory;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/list/TodaySection", loadOnStartup = 1)
@MultipartConfig
public class TodaySectionController extends SomeDaySectionController {

    @Override
    public void init() {
        section = SectionFactory.getTodaySection();
    }
}
