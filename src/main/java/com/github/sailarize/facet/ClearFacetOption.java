package com.github.sailarize.facet;

import java.util.Collection;

import com.github.sailarize.url.Filter;

/**
 * A {@link FacetOption} that clears (removes) any applied facet option that
 * belongs to the same facet.
 * 
 * @author agusmunioz
 * 
 */
public class ClearFacetOption extends BaseFacetOption {

    /**
     * Creates an initialized {@link ClearFacetOption}.
     * 
     * @param facet
     *            the facet name.
     * 
     * @param value
     *            the option value.
     */
    public ClearFacetOption(String facet, String value) {

        super(facet, value);
    }

    @Override
    protected boolean isCompatible(Filter filter) {

        return !this.getFacet().equals(filter.getName());
    }

    @Override
    public void apply(Collection<Filter> filters) {

    }

    @Override
    public boolean isApplied(Collection<Filter> filters) {

        return false;
    }

}
