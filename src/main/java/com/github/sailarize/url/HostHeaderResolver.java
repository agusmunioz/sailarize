package com.github.sailarize.url;

import javax.servlet.http.HttpServletRequest;

/**
 * A {@link HostResolver} that takes the host from the standard HTTP header
 * Host.
 * 
 * @author agusmunioz
 *
 */
public class HostHeaderResolver implements HostResolver {

    @Override
    public String resolve(HttpServletRequest request) {
        return request.getHeader("Host");
    }

}
