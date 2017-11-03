package com.github.sailarize.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.github.sailarize.http.Header;
import com.github.sailarize.http.HeaderHolder;
import com.github.sailarize.http.ParameterHolder;
import com.github.sailarize.properties.Titles;
import com.github.sailarize.url.HostHeaderResolver;
import com.github.sailarize.url.HostResolver;
import com.github.sailarize.url.PathHolder;
import com.github.sailarize.url.QueryString;

/**
 * A {@link Filter} that initializes sailarize components. It sets the current
 * {@link HttpServletRequest} in {@link RequestHolder} and sets the API path
 * (protocol, domain and application path) in {@link PathHolder}.
 * 
 * @author agusmunioz
 * 
 */
public class SailarizeFilter implements Filter {

    private static final String RELATIVE = "relative";

    private static final String SLASH = "/";

    private String path;

    private Boolean holdRequest = Boolean.TRUE;

    private HostResolver hostResolver;

    /**
     * The list of headers that must be added in all links and forms except in
     * cross-domain cases.
     */
    private Collection<String> headers;

    private String hypermedia;

    /**
     * The list of parameters that must be added in all links and forms except
     * in cross-domain cases.
     */
    private Collection<String> parameters;

    @Override
    public void init(FilterConfig config) throws ServletException {

        this.path = config.getInitParameter("path");

        if (this.path == null) {
            this.path = "";
        }

        if (!this.path.isEmpty() && !this.path.startsWith(SLASH)) {
            this.path = SLASH + this.path;
        }

        if (config.getInitParameter("holdRequest") != null) {
            this.holdRequest = Boolean.valueOf(config.getInitParameter("holdRequest"));
        }

        if (config.getInitParameter("headers") != null) {
            this.headers = Arrays.asList(config.getInitParameter("headers").split(","));
        }

        if (config.getInitParameter("hypermedia") != null) {
            this.hypermedia = config.getInitParameter("hypermedia");
        }

        if (config.getInitParameter("encoding") != null) {
            Titles.encoding(config.getInitParameter("encoding"));
        }

        if (config.getInitParameter("parameters") != null) {
            this.parameters = Arrays.asList(config.getInitParameter("parameters").split(","));
        }

        this.hostResolver = this.getHostResolver(config.getInitParameter("hostResolver"));

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (this.holdRequest) {
            RequestHolder.set(httpRequest);
        }

        PathHolder.set(this.getPath(httpRequest));

        if (headers != null) {
            HeaderHolder.set(this.headers(httpRequest));
        }

        if (this.parameters != null) {

            // TODO:refactor.

            QueryString query = new QueryString();

            for (String parameter : this.parameters) {

                String value = httpRequest.getParameter(parameter);

                if (value != null) {
                    query.add(parameter, value);
                }

            }

            ParameterHolder.set(query);
        }

        chain.doFilter(request, response);

        this.clean();
    }

    /**
     * Gets the API path, that is to say, the scheme + the domain + the
     * contextPath.
     * 
     * @param request
     *            the current http request.
     * 
     * @return the path.
     */
    private String getPath(HttpServletRequest request) {

        StringBuilder builder = new StringBuilder();

        if (!RELATIVE.equals(this.hypermedia)) {
            builder.append(request.getScheme()).append("://").append(this.hostResolver.resolve(request));
        }

        return builder.append(request.getContextPath()).append(this.path).toString();
    }

    /**
     * Extracts the list of headers that must be added in all links and forms.
     * 
     * @param request
     *            the current http request.
     * 
     * @return the list of headers.
     */
    private Collection<Header> headers(HttpServletRequest request) {

        Collection<Header> retain = new LinkedList<Header>();

        for (String header : this.headers) {

            String value = request.getHeader(header);

            if (value != null) {
                retain.add(new Header(header, value));
            }
        }

        return retain;
    }

    /**
     * Builds the host resolver.
     * 
     * @param type
     *            a fully qualified class name that implements
     *            {@link HostResolver}.
     * 
     * @return the host resolver.
     * 
     * @throws ServletException
     *             if there was an error when instantiating the host resolver
     */
    private HostResolver getHostResolver(String type) throws ServletException {

        if (type != null) {

            try {

                return (HostResolver) Class.forName(type).newInstance();

            } catch (Exception e) {
                throw new ServletException(e);
            }

        }

        return new HostHeaderResolver();
    }

    /**
     * Cleans all ThreadLocals
     */
    private void clean() {

        PathHolder.clean();

        if (this.headers != null) {
            HeaderHolder.clean();
        }

        if (this.holdRequest) {
            RequestHolder.clean();
        }

        if (this.parameters != null) {
            ParameterHolder.clean();
        }
    }

    @Override
    public void destroy() {
        this.clean();
    }

}
