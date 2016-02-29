package com.github.sailarize.url;

import java.util.Objects;

import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models a resource filter. It is an abstraction of an HTTP query parameter.
 * 
 * @author agusmunioz
 * 
 */
public class Filter {

    private String name;

    private String value;

    /**
     * Creates an initialized {@link Filter}.
     * 
     * @param name
     *            the filter name.
     * @param value
     *            the filter value.
     */
    public Filter(String name, String value) {

        this.name = name;
        this.value = value;
    }

    /**
     * Gets the filter name.
     * 
     * @return the name.
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the filter name.
     * 
     * @param name
     *            a name used as the query parameter name.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Gets the filter value.
     * 
     * @return the value used as a query parameter value.
     */
    public String getValue() {

        return value;
    }

    /**
     * Sets the filter value.
     * 
     * @param value
     *            a value used as a query parameter value.
     */
    public void setValue(String value) {

        this.value = value;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.name, this.value);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || !(obj instanceof Filter)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Filter other = (Filter) obj;

        boolean sameName = (this.name == null && other.getName() == null)
                || (this.name != null && this.name.equals(other.getName()));

        boolean sameValue = (this.value == null && other.getValue() == null)
                || (this.value != null && this.value.equals(other.getValue()));

        return sameName && sameValue;
    }
}
