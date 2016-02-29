package com.github.sailarize.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.github.sailarize.url.PathHolder;

/**
 * A {@link Filter} that initializes sailarize components. It sets the current
 * {@link HttpServletRequest} in {@link RequestHolder} and sets the API path
 * (protocol, domain and application path) in {@link PathHolder}.
 * 
 * @author agusmunioz
 * 
 */
public class SailarizeFilter implements Filter {

	private String path;

	private Boolean holdRequest = Boolean.TRUE;

	@Override
	public void init(FilterConfig config) throws ServletException {

		this.path = config.getInitParameter("path");

		if (this.path == null) {
			this.path = "";
		}

		if (!this.path.isEmpty() && !this.path.startsWith("/")) {
			this.path = "/" + this.path;
		}

		if (config.getInitParameter("holdRequest") != null) {
			this.holdRequest = Boolean.valueOf(config
					.getInitParameter("holdRequest"));
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (this.holdRequest) {
			RequestHolder.set(httpRequest);
		}

		PathHolder.set(this.getPath(httpRequest));

		chain.doFilter(request, response);

		PathHolder.clean();

		if (this.holdRequest) {
			RequestHolder.clean();
		}
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

		String scheme = request.getScheme() + "://";

		String domain = request.getHeader("host");

		String context = request.getContextPath();

		return scheme + domain + context + this.path;
	}

	@Override
	public void destroy() {
		PathHolder.clean();
		RequestHolder.clean();
	}

}
