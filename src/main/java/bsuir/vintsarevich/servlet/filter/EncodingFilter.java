package bsuir.vintsarevich.servlet.filter;

import bsuir.vintsarevich.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(filterName = "EncodingFilter",
        urlPatterns = {"/cafe.by/*"},
        initParams = {
                @WebInitParam(name = "characterEncoding", value = "utf-8")})

public class EncodingFilter implements Filter {
    private String code;

    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }
    public void destroy() {
        code = null;
    }
}

