package com.github.sailarize.form;

import com.github.sailarize.utils.ToStringBuilder;

public abstract class FormInput {

    private String id;

    private String name;

    private String title;

    /**
     * Creates an initialized {@link FormInput}.
     * 
     * @param name
     *            the input name, used when the form is submitted.
     */
    public FormInput(String name) {

        this.name = name;
    }

    public FormInput(String id, String name) {
        this(name);
        this.id = id;
    }

    /**
     * The input id for quick access.
     * 
     * @return the id.
     */
    public String getId() {

        return id;
    }

    /**
     * Sets the input id for quick access.
     * 
     * @param id
     *            a unique id.
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * Gets the input name used as a parameter (or field) when submitting.
     * 
     * @return the input name.
     */
    public String getName() {

        return name;
    }

    /**
     * Sets the input name used as a parameter (or field) when submitting.
     * 
     * @param name
     *            the input name.
     */
    public void setName(String name) {

        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return ToStringBuilder.toString(this);
    }
}
