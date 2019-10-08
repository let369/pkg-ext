package org.example.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtensionsPathFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(ExtensionsPathFilter.class);
    private final String OPENUI_WEBAPP_PATH = "/openui/";
    private final String EXTENSIONS_PARAM = "extensions";
    private List<String> extensions;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        String configuredExtensions = filterConfig.getInitParameter(EXTENSIONS_PARAM);
        extensions = Arrays.asList(configuredExtensions.split(","));
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final String path = ((HttpServletRequest) request).getRequestURI();
        final String extension = path.split("/")[2];
        if(extensions.contains(extension)) {
            if (path.equals(OPENUI_WEBAPP_PATH + extension) || path.equals(OPENUI_WEBAPP_PATH + extension + "/") || path.contains(".")) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/" + extension + "/").forward(request, response);
                log.debug("Path {} of the request is nested route of the extension {}. Call is forwarded to extensions root.", path, extension);
            }
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
            log.debug("Extension {} is not included in the initialisation parameter of the filter.", extension);
        }
    }

    @Override
    public void destroy() {
        extensions.clear();
    }

}
