package by.gsu.epamlab.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.Constants.*;

public abstract class AbstractFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AbstractFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
        } catch (ClassCastException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new ServletException(NON_HTTP_REQUEST_OR_RESPONSE);
        }

        this.doHttpFilter(request, response, chain);
    }

    protected abstract void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }
}
