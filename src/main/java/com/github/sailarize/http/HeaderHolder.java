package com.github.sailarize.http;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * Holder for HTTP headers that must be added in all links and forms. It uses a
 * {@link ThreadLocal} so {@link HeaderHolder#clean()} must be used in order to
 * properly clean the TreadLocal.
 * 
 * @author agusmunioz
 * 
 */
public class HeaderHolder {

    private static ThreadLocal<Collection<Header>> HEADERS = new ThreadLocal<Collection<Header>>();

    /**
     * Sets the list of headers that must be added in all links and forms.
     * 
     * @param headers
     *            this list of headers.
     */
    public static void set(Collection<Header> headers) {

        HEADERS.set(headers);
    }

    /**
     * Gets the current list of headers that must be added in all links and
     * forms.
     * 
     * @return this list of headers.
     */
    public static Collection<Header> get() {

        if (HEADERS.get() == null) {
            set(new LinkedList<Header>());
        }

        return HEADERS.get();
    }

    /**
     * Cleans the {@link ThreadLocal}.
     */
    public static void clean() {
        HEADERS.remove();
    }
}
