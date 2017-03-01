package com.github.sailarize.page;

/**
 * Constants for page links.
 * 
 * @author agusmunioz
 *
 */
public interface PageConstants {

    /**
     * The link group for pagination.
     */
    String GROUP = "pagination";

    /**
     * The page number parameter.
     */
    String PAGE_PARAM = "page";

    /**
     * The page size parameter.
     */
    String SIZE_PARAM = "pageSize";

    /**
     * rel value for next page link.
     */
    String NEXT_REL = "next";

    /**
     * rel value for next page link.
     */
    String PREVIOUS_REL = "previous";

    /**
     * Key for 'next' link title in properties files.
     */
    String NEXT_TITLE = "pagination.next";

    /**
     * Key for 'previous' link title in properties files.
     */
    String PREVIOUS_TITLE = "pagination.previous";

    /**
     * Key for 'first page' link title in properties files.
     */
    String FIRST_REL = "first";

    /**
     * Key for 'last page' link title in properties files.
     */
    String LAST_REL = "last";
}
