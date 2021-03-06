package com.github.sailarize.facet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.github.sailarize.http.Header;
import com.github.sailarize.url.Filter;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * An abstract {@link FacetOption} implementation with the base common
 * information and behavior.
 * 
 * @author agusmunioz
 * 
 */
public abstract class BaseFacetOption implements FacetOption {

    private String facet;

    private String name;

    private String value;

    private String title;

    private Map<String, Object> data;

    private Collection<Header> headers;

    /**
     * Creates an initialized {@link BaseFacetOption}.
     * 
     * @param facet
     *            the facet name this option belongs to.
     * 
     * @param value
     *            the option value.
     */
    public BaseFacetOption(String facet, String value) {

        this.facet = facet;
        this.name = value;
        this.value = value;
    }

    /**
     * Creates an initialized {@link BaseFacetOption}.
     * 
     * @param facet
     *            the facet name this option belongs to.
     * 
     * @param value
     *            the option value.
     * 
     * @param title
     *            the option title.
     */
    public BaseFacetOption(String facet, String value, String title) {
        this(facet, value);
        this.title = title;
    }

    @Override
    public String getFacet() {

        return facet;
    }

    /**
     * Sets the facet name this option belongs to.
     * 
     * @param name
     *            the facet name.
     */
    public void setFacet(String name) {

        this.facet = name;
    }

    @Override
    public String getName() {

        return name;
    }

    /**
     * Sets the option name.
     * 
     * @param name
     *            the name.
     */
    public void setName(String name) {

        this.name = name;
    }

    @Override
    public String getValue() {

        return this.value;
    }

    /**
     * Sets the option value.
     * 
     * @param value
     *            the value.
     */
    public void setValue(String value) {

        this.value = value;
    }

    @Override
    public String getTitle() {

        return this.title;
    }

    /**
     * Sets the option title.
     * 
     * @param title
     *            the title.
     */
    public void setTitle(String title) {

        this.title = title;
    }

    @Override
    public Map<String, Object> getData() {

        return this.data;
    }

    /**
     * Sets the option extra data.
     * 
     * @param data
     *            any extra data to be added in the link.
     */
    public void setData(Map<String, Object> data) {

        this.data = data;
    }

    /**
     * Adds a specific extra data to the facet option.
     * 
     * @param name
     *            the extra data name.
     * @param value
     *            the extra data value.
     */
    public void addData(String name, Object value) {

        if (this.data == null) {
            this.data = new HashMap<String, Object>();
        }

        this.data.put(name, value);
    }

    @Override
    public boolean isApplied(Collection<Filter> filters) {

        if (filters == null) {
            return false;
        }

        Boolean result = Boolean.FALSE;

        for (Filter filter : filters) {
            if (this.getFacet().equals(filter.getName())) {
                result |= filter.getValue().equals(this.getValue());
            }
        }
        return result;
    }

    @Override
    public Collection<Filter> compatibles(Collection<Filter> filters) {

        Collection<Filter> compatibles = new LinkedList<Filter>();

        for (Filter filter : filters) {

            if (this.isCompatible(filter)) {
                compatibles.add(filter);
            }
        }

        return compatibles;
    }

    public void apply(Collection<Filter> filters) {

        filters.add(new Filter(this.getFacet(), this.getValue()));
    }

    @Override
    public Collection<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(Collection<Header> headers) {
        this.headers = headers;
    }

    /**
     * Determines if a filter is compatible with this facet option.
     * 
     * @param fiter
     *            the filter to evaluate compatibility.
     * 
     * @return if it is compatible or not.
     */
    protected abstract boolean isCompatible(Filter fiter);

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }
}
