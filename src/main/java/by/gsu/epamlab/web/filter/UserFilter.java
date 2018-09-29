package by.gsu.epamlab.web.filter;

import by.gsu.epamlab.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static by.gsu.epamlab.Constants.INDEX_JSP;
import static by.gsu.epamlab.Constants.SLASH;
import static by.gsu.epamlab.Constants.USER;

@WebFilter(urlPatterns = "/list/*")
public class UserFilter extends AbstractFilter {


    @Override
    protected void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute(USER);
        if (user == null) {
            resp.sendRedirect(INDEX_JSP);
        }
        chain.doFilter(req, resp);
    }

}


