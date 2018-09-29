package by.gsu.epamlab.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.Constants.*;

@WebFilter(urlPatterns = "/*", initParams = {@WebInitParam(name = ENCODING, value = UTF_8)})
public class EncodingFilter extends AbstractFilter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter(ENCODING);

    }

    @Override
    protected void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String codeRequest = req.getCharacterEncoding();
        if (code != null && code.equalsIgnoreCase(codeRequest)) {
            req.setCharacterEncoding(code);
            resp.setCharacterEncoding(code);
        }
        resp.setContentType(TEXT_HTML);
        chain.doFilter(req, resp);
    }


}


