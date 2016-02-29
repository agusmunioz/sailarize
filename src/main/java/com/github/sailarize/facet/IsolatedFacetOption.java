package com.github.sailarize.facet;

import java.util.HashSet;
import java.util.Set;

import com.github.sailarize.url.Filter;

/**
 * A {@link FacetOption} that works isolated from the rest of facets, that is to
 * say, it removes any other applied filter leaving only this option (with the
 * exception of {@link IsolatedFacetOption#retain(String...)} values).
 * 
 * @author agusmunioz
 * 
 */
public class IsolatedFacetOption extends BaseFacetOption {

	private Set<String> retain;

	/**
	 * Creates an initialized {@link IsolatedFacetOption}.
	 * 
	 * @param facet
	 *            the facet name this option belongs to.
	 * 
	 * @param value
	 *            the option value.
	 */
	public IsolatedFacetOption(String facet, String value) {

		super(facet, value);
		this.retain = new HashSet<String>();
	}

	/**
	 * Sets which filters must be retained in the link.
	 * 
	 * @param filters
	 *            the filters (parameter) names.
	 */
	public void retain(String... filters) {

		for (String filter : filters) {

			retain.add(filter);
		}
	}

	@Override
	protected boolean isCompatible(Filter filter) {

		return this.retain.contains(filter.getName());
	}

}
