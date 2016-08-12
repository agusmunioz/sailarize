package com.github.sailarize.facet;

import com.github.sailarize.url.Filter;

/**
 * A {@link FacetOption} that works as an exclusive filter, that is to say, only
 * one option can be used in a link at a time.
 * 
 * @author agusmunioz
 * 
 */
public class ExclusiveFacetOption extends BaseFacetOption {

    /**
     * Creates an initialized {@link ExclusiveFacetOption}.
     * 
     * @param facet
     *            the facet name this option belongs to.
     * 
     * @param value
     *            the option value.
     */
    public ExclusiveFacetOption(String facet, String value) {

        super(facet, value);
    }

    @Override
    protected boolean isCompatible(Filter filter) {

        return !this.getFacet().equals(filter.getName());
    }

}
