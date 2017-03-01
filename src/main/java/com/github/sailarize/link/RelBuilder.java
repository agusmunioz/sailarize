package com.github.sailarize.link;

import com.github.sailarize.utils.StringUtils;

/**
 * Builder for Link rel field values.
 * 
 * @author agusmunioz
 * 
 */
public class RelBuilder {

    /**
     * Builds a link rel field value.
     * 
     * @param prefix
     *            the prefix of the rel value. Is optional, can be null or
     *            empty.
     * 
     * @param name
     *            the rel value.
     * 
     * @return a camel case rel value.
     * 
     * @throws IllegalArgumentException
     *             if a null name is provided.
     */
    public static String rel(String prefix, Object name) {

        if (name == null) {

            throw new IllegalArgumentException("name parameter must be provided");
        }

        String rel = name.toString().toLowerCase();

        if (prefix != null && !prefix.isEmpty()) {

            rel = prefix + StringUtils.capitalize(rel);
        }

        return rel;
    }

    /**
     * Builds a link rel field value.
     * 
     * @param prefix
     *            the prefix of the rel value. Is optional, can be null or
     *            empty.
     * 
     * @param name
     *            the rel value.
     * 
     * @param postFix
     *            the rel postfix to append at the end of the rel value.
     * 
     * @return a camel case rel value.
     * 
     * @throws IllegalArgumentException
     *             if a null name is provided.
     */
    public static String rel(String prefix, Object name, String postFix) {

        return new StringBuilder(rel(prefix, name)).append(StringUtils.capitalize(postFix)).toString();
    }

}
