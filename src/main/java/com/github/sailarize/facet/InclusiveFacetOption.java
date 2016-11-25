package com.github.sailarize.facet;

import com.github.sailarize.url.Filter;

/**
 * A {@link FacetOption} that works as an inclusive filter, that is to say, more
 * than one option, belonging to the same facet and with different values, can
 * be used in a URL at the same time.
 * 
 * @author agusmunioz
 * 
 */
public class InclusiveFacetOption extends BaseFacetOption {

	/**
	 * Creates an initialized {@link InclusiveFacetOption}.
	 * 
	 * @param facet
	 *            the facet name this option belongs to.
	 * @param value
	 *            the option value.
	 */
	public InclusiveFacetOption(String facet, String value) {

		super(facet, value);
	}

	/**
	 * Creates an initialized {@link InclusiveFacetOption}.
	 * 
	 * @param facet
	 *            the facet name this option belongs to.
	 * @param value
	 *            the option value.
	 * @param title
	 *            the option title.
	 */
	public InclusiveFacetOption(String facet, String value, String title) {

		super(facet, value, title);
	}

	@Override
	protected boolean isCompatible(Filter filter) {

		return !this.getFacet().equals(filter.getName()) || !this.getValue().equals(filter.getValue());
	}

}
