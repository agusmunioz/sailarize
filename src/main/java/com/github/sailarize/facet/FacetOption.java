package com.github.sailarize.facet;

import java.util.Collection;
import java.util.Map;

import com.github.sailarize.url.Filter;

/**
 * Models a facet (or filter) information used for building facets links.
 * 
 * @author agusmunioz
 * 
 */
public interface FacetOption {

    /**
     * Gets the facet name this option belongs to.
     * 
     * @return the name.
     */
    String getFacet();

    /**
     * The facet option name. It is used for building links rels.
     * 
     * @return the name.
     */
    String getName();

    /**
     * Gets the option value.
     * 
     * @return the option value.
     */
    String getValue();

    /**
     * The facet option title, used as the link title.
     * 
     * @return the title.
     */
    String getTitle();

    /**
     * Extra data to be added to the link.
     * 
     * @return a map where the key is the property name in the link and its
     *         associated value.
     */
    Map<String, Object> getData();

    /**
     * Determines which filters are compatible with this option.
     * 
     * @param filters
     *            the list of filters to evaluate.
     * 
     * @return a not null list of compatible filters.
     */
    Collection<Filter> compatibles(Collection<Filter> filters);

    /**
     * Determines if the facet option appears as a filter. That is to say, it is
     * applied.
     * 
     * @param filters
     *            the filter where to look for.
     * 
     * @return true if it is applied, false otherwise.
     */
    boolean isApplied(Collection<Filter> filters);

    /**
     * Adds filters associated to this facet options.
     * 
     * @param filters
     *            the filter list where to add the new filters.
     */
    void apply(Collection<Filter> filters);
}
