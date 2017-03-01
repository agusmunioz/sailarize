package com.github.sailarize.url;

import javax.servlet.http.HttpServletRequest;

public interface HostResolver {

    String resolve(HttpServletRequest request);

}
