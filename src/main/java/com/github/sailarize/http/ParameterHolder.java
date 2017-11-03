package com.github.sailarize.http;

public class ParameterHolder {

    private static ThreadLocal<String> PARAMETERS = new ThreadLocal<String>();

    /**
     * Sets the parameters that must be added in all links and forms.
     * 
     * @param headers
     *            this list of headers.
     */
    public static void set(String parameters) {

        PARAMETERS.set(parameters);
    }

    /**
     * Gets the current list of parameters that must be added in all links and
     * forms.
     * 
     * @return this list of parameters.
     */
    public static String get() {

        if (PARAMETERS.get() == null) {
            set("");
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
