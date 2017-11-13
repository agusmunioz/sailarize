package com.github.sailarize.http;

import com.github.sailarize.url.QueryString;

/**
 * Holder for URL parameters used for parameter propagation.
 * 
 * @author agustinmunoz
 *
 */
public class ParameterHolder {

    private static ThreadLocal<QueryString> PARAMETERS = new ThreadLocal<QueryString>();

    /**
     * Sets the parameters that must be added in all links and forms.
     * 
     * @param headers
     *            this list of headers.
     */
    public static void set(QueryString parameters) {

        PARAMETERS.set(parameters);
    }

    /**
     * Gets the current list of parameters that must be added in all links and
     * forms.
     * 
     * @return this list of parameters.
     */
    public static QueryString get() {

        if (PARAMETERS.get() == null) {
            set(new QueryString());
        }

        return PARAMETERS.get();
    }

    /**
     * Cleans the {@link ThreadLocal}.
     */
    public static void clean() {
        PARAMETERS.remove();
    }
}
