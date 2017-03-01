package com.github.sailarize.url;

import com.github.sailarize.servlet.RequestHolder;

/**
 * 
 * Utility class for operating over HTTP domains.
 * 
 * @author agusmunioz
 *
 */
public class Domain {

    private static final String PROTOCOL_RELATIVE = "//";

    /**
     * Determines if the URL is cross-domain. That is to say, it references a
     * different host/domain.
     * 
     * @param url
     *            the url.
     * 
     * @return true if it is cross-domain, false otherwise.
     */
    public static boolean cross(String url) {

        String target = url;

        if (url.startsWith(PROTOCOL_RELATIVE)) {
            target = url.replaceFirst(PROTOCOL_RELATIVE, "");

        }

        String domain = (RequestHolder.get() != null) ? RequestHolder.get().getServerName() : "";

        return !target.contains(domain) && !target.startsWith("/");
    }
}
