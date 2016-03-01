package com.github.sailarize.form;

import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models a Hypermedia form input that indicates a field/parameter to be
 * submitted with the form.
 * 
 * @author agusmunioz
 * 
 */
public class FormInput {

    private String id;

    private String name;

    private Object value;

    private String mask;

    /**
     * Creates an initialized {@link FormInput}.
     * 
     * @param name
     *            the input name, used when the form is submitted.
     */
    public FormInput(String name) {

        this.name = name;
    }

    /**
     * Creates an initialized {@link FormInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submitting.
     * 
     * @param value
     *            the input value.
     */
    public FormInput(String name, Object value) {

        this(name);
        this.value = value;
    }

    /**
     * Creates an initialized {@link FormInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submit.
     */
    public FormInput(String id, String name) {

        this(name);
        this.id = id;
    }

    /**
     * Creates an initialized {@link FormInput}.
     * 
     * @param id
     *            the input id for direct access.
     * 
     * @param name
     *            the input name, used as the parameter name when submitting.
     * 
     * @param value
     *            the input value.
     */
    public FormInput(String id, String name, Object value) {

        this(id, name);
        this.value = value;
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

    /**
     * Gets the input value.
     * 
     * @return the value.
     */
    public Object getValue() {

        return value;
    }

    /**
     * Sets the input value.
     * 
     * @param value
     *            a value.
     */
    public void setValue(Object value) {

        this.value = value;
    }

    /**
     * Gets the input mask used for validating the input value.
     * 
     * @return the mask or null if not set.
     */
    public String getMask() {

        return mask;
    }

    /**
     * Sets the input mask used for validation the input value.
     * 
     * @param mask
     *            a regular expression.
     */
    public void setMask(String mask) {

        this.mask = mask;
    }

    @Override
    public String toString() {

        return ToStringBuilder.toString(this);
    }

}
