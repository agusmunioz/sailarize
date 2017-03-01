package com.github.sailarize.url;

/**
 * 
 * Holder for the Web Application path used in API links. It uses
 * {@link ThreadLocal} so {@link PathHolder#clean()} must be used in order to
 * properly clean the TreadLocal.
 * 
 * @author agusmunioz
 * 
 */
public class PathHolder {

    private static ThreadLocal<String> PATH = new ThreadLocal<String>();

    /**
     * Sets the current API Path.
     * 
     * @param path
     *            the path.
     */
    public static void set(String path) {

        PATH.set(path);
    }

    /**
     * Gets the current API path.
     * 
     * @return the path or an empty {@link String} is not set.
     */
    public static String get() {

        if (PATH.get() == null) {
            return "";
        }

        return PATH.get();
    }

    /**
     * Cleans the {@link ThreadLocal}.
     */
    public static void clean() {
        PATH.remove();
    }
}
