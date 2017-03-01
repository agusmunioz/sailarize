package com.github.sailarize.sort;

import com.github.sailarize.utils.ToStringBuilder;

/**
 * A {@link SortOption} basic implementation with the minimum required for
 * specifying a sort option.
 * 
 * @author agusmunioz
 * 
 */
public class SimpleSortOption implements SortOption {

    private String value;

    private String direction;

    private String title;

    /**
     * Creates an initialized {@link SortOption}.
     * 
     * @param value
     *            the sort value used by the application.
     * 
     * @param direction
     *            a value that indicates the sort direction. This is defined by
     *            the application.
     */
    public SimpleSortOption(String value, String direction) {
        this.value = value;
        this.direction = direction;
    }

    /**
     * Creates an initialized {@link SortOption}.
     * 
     * @param value
     *            the sort value used by the application.
     * 
     * @param direction
     *            a value that indicates the sort direction. This is defined by
     *            the application.
     * 
     * @param title
     *            the sort title used in the link.
     */
    public SimpleSortOption(String value, String direction, String title) {
        this(value, direction);
        this.title = title;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the sort value.
     * 
     * @param value
     *            an application level value used for identifying the type of
     *            sort.
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the sort direction.
     * 
     * @param direction
     *            an application level value that indicates if the sort
     *            direction is ascending or descending.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * The title of the sort in the link.
     * 
     * @param title
     *            a user friendly text that describes the sort. It is not
     *            required.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }
}
