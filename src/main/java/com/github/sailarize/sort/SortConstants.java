package com.github.sailarize.sort;

/**
 * Constants for building hypermedia links related to sorting.
 * 
 * @author agusmunioz
 * 
 */
public interface SortConstants {

    /**
     * Links rel prefix for sorting.
     */
    String REL = "by";

    /**
     * Sail link group for sort links.
     */
    String GROUP = "sort";

    /**
     * HTTP parameter for specifying the sort.
     */
    String SORT_BY = "sortBy";

    /**
     * HTTP parameter for specifying the sort direction.
     */
    String SORT_DIRECTION = "sortDirection";

    /**
     * HTTP parameter value for descending direction.
     */
    String DESCENDING = "DESC";

    /**
     * HTTP parameter value for ascending direction.
     */
    String ASCENDING = "ASC";

    /**
     * A String template for building the titles key in properties files.
     */
    String TITLE_KEY = "sort.%s.%s";

}
